<%@ page errorPage="/ErrorServlet" %>
<html>
    <head>
        <title>This will never be seen</title>
    </head>
    <body>
    <%
    if( true )
    {
        throw new IllegalStateException("This is an exception foo!!!  Please catch me");
    }
    %>
    </body>
</html>