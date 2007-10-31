<html>
    <head>
        <title>Embedded Applet Example</title>
        <link rel="stylesheet" type="text/css" href="/format.css"/>
    </head>
    <body>
        <h1>Embedded Applet</h1>
        <hr/>
        <p>The following JSP code shows how to embed an applet into a JSP page.  Please note 
        that this will not work whil the class file lives in the WEB-INF area of the web app.</p>
        <hr/>
        <jsp:plugin code="FooApplet.class" codebase="" type="applet" height="100" width="100" jreversion="1.2">
            <jsp:fallback>Applet support not found, can't run example</jsp:fallback>
        </jsp:plugin>
        
    </body>
</html>