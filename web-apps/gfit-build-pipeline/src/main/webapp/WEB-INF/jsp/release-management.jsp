<%@ include file="/WEB-INF/jsp/includes/includes.jsp" %>
<html>
<head>
    <title>Release Management</title>
</head>
<body>

<h2>Release Management</h2>
<p/>
<h3>Defined Releases</h3>
<table>
    <thead>
    <th>Release ID</th>
    <th>Description</th>
    </thead>
    <c:forEach var="rv" items="${releaseVersions}">
        <tr>
            <td>${rv.version}</td>
            <td>${rv.description}</td>
        </tr>
    </c:forEach>
</table>
<table class="table-buttons">
    <tr>
        <td colspan="2" align="centre">
            <spring:url value="/release-management/new" var="addUrl"/>
            <a id="newVersionLink" href="${fn:escapeXml(addUrl)}">New Release Version</a>
        </td>
    </tr>
</table>

</body>
</html>