<html>
    <head>
        <title>Use Bean example</title>
        <link rel="stylesheet" type="text/css" href="/format.css"/>
    </head>
    <body>
        <jsp:useBean id="usr" class="org.suggs.sandbox_webapps.javabeans.User" scope="session"/>
        <%
        usr.setName("Peter");
        usr.setPassword_("fooBar");
        %>
        <h3>User java bean created</h3>
        <hr/>
        Hello <jsp:getProperty name="usr" property="name"/>, welcome to the web app.
    </body>
</html>    