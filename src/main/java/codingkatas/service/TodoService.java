package codingkatas.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/Todo")
public class TodoService {

    private static List<Todo> todos = new ArrayList<Todo>();

    static {
        Todo t1 = new Todo();
        t1.setId(1);
        t1.setDone(Boolean.FALSE);
        t1.setSubject("Take the dishes");
        todos.add(t1);

        Todo t2 = new Todo();
        t2.setId(2);
        t2.setDone(Boolean.FALSE);
        t2.setSubject("Do my homework");
        todos.add(t2);

        Todo t3 = new Todo();
        t3.setId(3);
        t3.setDone(Boolean.FALSE);
        t3.setSubject("Cook dinner");
        todos.add(t3);


    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Todo todo) {
        todos.add(todo);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Todo get(@PathParam("id") int id) {
        return todos.get(id);
    }

    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Todo todo, @PathParam("id") int id) {
        todos.add(id, todo);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") int id) {
        todos.remove(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Todo> list() {
        return todos;
    }
}
