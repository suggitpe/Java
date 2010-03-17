<html>
    <head>
        <title>Play JSP</title>
        <link rel="stylesheet" type="text/css" href="/format.css"/>
    </head>
    <body>
    
        <%
        String[] strings = new String[4];
        strings[0] = "alpha";
        strings[1] = "beta";
        strings[2] = "gamma";
        strings[3] = "omega";
        for( int i = 0; i < strings.length; ++i )
        {
        %>
        Strings[<%= i %>] = <%= strings[i] %><br/>
        <% } %>
    
    </body>
</html>