<?xml version="1.0" encoding="UTF-8"?>
<html>
    <head>
        <title>Embedded Applet Example</title>
        <link rel="stylesheet" type="text/css" href="/format.css"/>
    </head>
    <body>
        <jsp:plugin code="FooApplet.class" codebase="" type="applet" height="100" width="100" jreversion="1.2">
            <jsp:fallback>Applet support not found, can't run example</jsp:fallback>
        </jsp:plugin>
    </body>
</html>