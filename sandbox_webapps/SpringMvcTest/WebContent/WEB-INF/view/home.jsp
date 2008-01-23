<%@ page contentType="text/html" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
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
                <li>${rant}</li>
            </c:forEach>            
        </ul>
    </body>
</html>