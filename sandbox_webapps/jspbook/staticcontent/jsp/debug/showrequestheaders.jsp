<%@ page import="java.util.*" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="/format.css"/>
        <title>Show Request Headers</title>
    </head>
    
    <body>
        <h1>Request Headers</h1>
        <hr/>
        <p>HTTP headers sent by the client:</p>
        <hr/>
        <ul>
        <%
        Enumeration e = request.getHeaderNames();
        while( e.hasMoreElements() )
        {
            String name = (String)e.nextElement();
            String value = request.getHeader(name);
        %>
        <li><b><%= name %></b>: <%= value %></li>
        <% } %>
        <ul>
    </body>
    
</html>
