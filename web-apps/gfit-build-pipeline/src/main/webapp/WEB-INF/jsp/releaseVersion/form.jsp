<%@ include file="/WEB-INF/jsp/includes/includes.jsp" %>
<html>
<head>
    <title>Release Version</title>
</head>
<body>

<c:choose>
    <c:when test="${releaseVersion.new}"><c:set var="method" value="post"/></c:when>
    <c:otherwise><c:set var="method" value="post"/></c:otherwise>
</c:choose>


<h2><c:if test="${releaseVersion.new}">New </c:if>Release Version</h2>

<form:form modelAttribute="releaseVersion" method="${method}">
    <table>
        <tr>
            <th>
                Description: <form:errors path="description" ccsClass="errors"/>
                <br/>
                <form:input path="description" size="30" maxlength="180"/>
            </th>
        </tr>
        <tr>
            <td>
                <c:choose>
                    <c:when test="${releaseVersion.new}">
                        <p class="submit"><input type="submit" value="Add New Release Version"/></p>
                    </c:when>
                    <c:otherwise>
                        <p class="submit"><input type="submit" value="Update Release Version"/></p>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>
</form:form>

<c:if test="${!releaseVersion.new}">
    <form:form method="delete">
        <p class="submit"><input type="submit" value="Delete Release Version"/></p>
    </form:form>
</c:if>

</body>
</html>
