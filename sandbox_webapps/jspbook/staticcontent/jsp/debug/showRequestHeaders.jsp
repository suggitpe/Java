<%@ taglib uri="http://www.suggs.org.uk/format/format" prefix="format" %>
<%@page import="java.util.Enumeration"%>
<html>
    <head>
        <title>Request headers</title>
        <format:includeFormat/>
    </head>
    <body>
        <h3>Request headers</h3>
        <hr/>
        The following are the request headers from this page.
        <ul>
        <%
        Enumeration e = request.getHeaderNames();
        while( e.hasMoreElements() )
        {
            String header = (String)e.nextElement();
            String value = request.getHeader( header);
        %>
            <li><b><%=header %></b>: <%= value %></li>
        <%} %>
        </ul>
    </body>
</html>