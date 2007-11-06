<%@ page isErrorPage="true" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="/format.css"/>
        <title>An error has occurred</title>
    </head>
    <body>
        <h3>An error has occurred</h3>
        <hr/>
        <p>
            Sorry, but this site is currently unavailable to render the 
            service you requested.  A bug in the system has caused this
            error to occur.  Please send adescription of the problem to
            <%= application.getInitParameter("AdminEmail") %>.  Thanks.</p>
            <p>The cause of the exception was '<code><%=exception.getMessage() %></code>'.</p>
            <hr/>
            <p><i>By the way I am a JSP page</i></p>
    </body>
</html>