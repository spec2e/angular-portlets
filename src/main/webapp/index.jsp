<!doctype html>
<html lang="da">
<head>

    <!-- Meta information -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Angular portlets</title>

    <!-- twitter bootstrap styles and app specific style. Note that the app.css i placed last - to override from bootstrap -->
    <link href="css/bootstrap-3.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap-3.0/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="css/app.css" rel="stylesheet">

    <!-- jQuery and AngularJS. jQuery must be first in line register it self for use by AngularJS -->
    <!-- Other utility libraries as well -->
    <script src="lib/jquery-2.0.3.min.js"></script>
    <script src="lib/angular/angular.min.js"></script>
    <script src="lib/angular/angular-resource.min.js"></script>
    <script src="lib/angular-ui-router.js"></script>
    <!-- Angular wrapper for twitter bootstrap widgets -->
    <script src="lib/ui-bootstrap-tpls-0.5.0.min.js"></script>
    <!-- Sugar JS. Utility for javascript -->
    <script src="lib/sugar-1.3.9.min.js"></script>

    <script src="lib/util.js"></script>

</head>

<body>

<div class="container" id="portletA">

    <div class="template">

        <jsp:include page="portletA/index.html"></jsp:include>

    </div>

</div>

<hr>

<div class="container" id="portletB">

    <div class="template">

        <jsp:include page="portletB/index.html"></jsp:include>

    </div>

</div>


</body>
</html>