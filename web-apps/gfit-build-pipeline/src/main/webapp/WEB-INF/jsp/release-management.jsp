<%@ include file="/WEB-INF/jsp/includes/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/includes/header.jsp" %>

<h2>Release Management</h2>

<p/>

<h3>Defined Releases</h3>
<table id="releasesTable">
    <thead>
    <th>Release ID</th>
    <th>Description</th>
    </thead>
    <c:forEach var="rv" items="${releaseVersions}">
        <tr id="${rv.description}">
            <td class="rvVersion">${rv.version}</td>
            <td class="rvDescription">${rv.description}</td>
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

<%@ include file="/WEB-INF/jsp/includes/footer.jsp" %>

