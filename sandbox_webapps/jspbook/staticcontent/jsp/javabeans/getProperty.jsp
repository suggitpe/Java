<html>
    <head>
        <title>Use Bean example</title>
        <link rel="stylesheet" type="text/css" href="/format.css"/>
    </head>
    <body>
        <jsp:useBean id="usr" class="org.suggs.sandbox_webapps.javabeans.User"/>
        <%
        // the following could also have been acomplished through the use of the <jsp:setProperty> tag
        usr.setName("Peter");
        %>
        <jsp:setProperty name="usr" property="password" value="foobar" />
        <h3>User java bean created</h3>
        <hr/>
        Hello <jsp:getProperty name="usr" property="name"/>, welcome to the web app.  <br/>
        <i>Don't tell anyone that your password is ${usr.password} </i>
    </body>
</html>    