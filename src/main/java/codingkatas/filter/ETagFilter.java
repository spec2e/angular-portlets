package codingkatas.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Søren Pedersen, soeren.pedersen@speconsult.net
 */
@WebFilter("/*")
public class ETagFilter implements Filter {

    private static String APPLICATION_JSON = "application/json";

    @Override
    public void init(FilterConfig fc) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        //Wrap the HttpServletResponse, so that we can take control
        ETagResponseWrapper wrapper = new ETagResponseWrapper(response);
        chain.doFilter(request, wrapper);

        //Test if this resource should get an ETag...
        if (isEligibleForETag(request, wrapper)) {
            //Get the response from the wrapper
            byte[] bytes = wrapper.toByteArray();

            //Create a MD5 Digest
            String token = '"' + getMd5Digest(bytes) + '"';
            response.setHeader(HTTPCacheHeaderEnum.ETAG.getName(), token);

            //Get the token of this resource which the browser has cached
            String previousToken = request.getHeader(HTTPCacheHeaderEnum.IF_NONE_MATCH.getName());

            //Test if they are the equal
            if (previousToken != null && previousToken.equals(token)) {
                //In this case they are equal, so return an HTTP 304
                response.sendError(HttpServletResponse.SC_NOT_MODIFIED);
            } else {
                //They were not equal, i.e. the resource has been updated. Return it with the new ETag digest
                copyBodyToResponse(wrapper.toByteArray(), response);
            }
        } else {
            //Something happened, so we could not give this resource an ETag. Just return the response
            copyBodyToResponse(wrapper.toByteArray(), response);
        }
    }

    private boolean isEligibleForETag(HttpServletRequest request, HttpServletResponseWrapper response) {

        String contentType = request.getContentType();
        if(contentType == null || contentType.contains(APPLICATION_JSON)) {
            return false;
        }

        return (response.getStatus() >= 200 && response.getStatus() < 300);
    }

    private void copyBodyToResponse(byte[] body, HttpServletResponse response) throws IOException {
        response.setContentLength(body.length);
        ServletOutputStream sos = response.getOutputStream();
        sos.write(body);
        sos.flush();
        sos.close();
    }

    private String getMd5Digest(byte[] bytes) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("cryptographic algorithm not available", e);
        }
        byte[] messageDigest = md.digest(bytes);
        BigInteger number = new BigInteger(1, messageDigest);
        StringBuffer sb = new StringBuffer('0');
        sb.append(number.toString(16));
        return sb.toString();
    }

    /**
     * Class that acts a mediator between the real response to the client.
     * The wrapper is used contain the file that was requested. Only if this file was modified, the response of this
     * wrapper is copied to the HttpServletResponse which is returned to the client.
     * If no changes was detected, the HttpServletResponse to the client will not contain any data, but only a
     * HTTP 304 (not modified)
     *
     */
    private static class ETagResponseWrapper extends HttpServletResponseWrapper {

        private final CharArrayWriter writer = new CharArrayWriter();

        public ETagResponseWrapper(final HttpServletResponse response) {
            super(response);
        }

        @Override
        public final PrintWriter getWriter() throws java.io.IOException {
            return new PrintWriter(writer);
        }

        @Override
        public final String toString() {
            return writer.toString();
        }

        public final byte[] toByteArray() throws UnsupportedEncodingException {
            return toString().getBytes("UTF-8");
        }
    }

}
