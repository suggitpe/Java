<%@ taglib uri="http://www.suggs.org.uk/format/format" prefix="format" %>
<%@page import="java.util.Date"%>
<%
    Cookie[] cookies = request.getCookies();
    String timestamp = new Date().toString();
    Cookie ts = new Cookie("timestamp", timestamp);
    response.addCookie(ts);
    Cookie lastVisit = null;
    for( int i = 0; i < cookies.length; ++i )
    {
        Cookie tmp = cookies[i];
        if( tmp.getName().equals("timestamp") )
        {
            lastVisit = tmp;
            break;
        }
    }
    
    if( lastVisit == null )
    {
        lastVisit = ts;
    }
    request.setAttribute("lastVisit", lastVisit);
    
%>
<html>
    <head>
        <title>Cookie Tracker</title>
        <format:includeFormat/>
    </head>
    <body>
        <h3>Cookie Tracker</h3>
        <hr/>
        You last visited this site on: ${lastVisit.value} 
    </body>
</html>