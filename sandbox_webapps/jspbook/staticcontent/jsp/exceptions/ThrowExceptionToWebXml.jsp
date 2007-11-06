<html>
    <head>
        <title>Throwing an exception</title>
    </head>
    <body>
    <%
    if( true )
    {
        throw new IllegalArgumentException("Agument exception bar .. to be caught by the exception handling defined in web.xml");
    }
    %>
    </body>
</html>