<%@ taglib uri="http://www.suggs.org.uk/format/format" prefix="format" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<html>
    <head>
        <title>Show Cookies</title>
        <format:includeFormat/>
    </head>
    <body>
        <h3>Show Cookies</h3>
        <hr/>
        <c:forEach var="c" begin="0" items="${cookie}" >
            Name: <b>${c.value.name}</b>, value: ${c.value.value}
        </c:forEach>
    </body>
</html>
