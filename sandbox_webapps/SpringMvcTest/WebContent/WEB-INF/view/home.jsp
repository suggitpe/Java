<%@ page contentType="text/html" %>
<%@include file="include/taglibs.jsp"%>
<html>
    <head>
        <title>Rantz</title>
    </head>
    <body>
        <h2>Welcome to RoadRantz!</h2>
        <hr/>
        
        <h3>Recent Rantz:</h3>
        <ul>
            <c:forEach items="${rants}" var="rant">
                <li>${rant.rantText}</li>
            </c:forEach>            
        </ul>
    </body>
</html>