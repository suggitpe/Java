<%@ page isErrorPage="true" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="/format.css"/>
        <title>File not found</title>
    </head>
    <body>
        <h3>File not found</h3>
        <hr/>
        <p>
            Sorry, but the file you have requested <%=request.getRequestURL() %> has 
            not been found, please can you try again later.  If you have any
            concerns about this please can you send a mail to 
            <%= application.getInitParameter("AdminEmail") %>.  Thanks.</p>
            <p>The cause of the exception was '<code><%=exception.getMessage() %></code>'.</p>
            <hr/>
            <p><i>By the way I am a JSP page</i></p>
    </body>
</html>