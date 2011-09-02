<%@ include file="/WEB-INF/jsp/includes/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/includes/header.jsp" %>

<h2 id="title">Release Management</h2>

<p/>

<h3>Defined Releases</h3>
<table id="releasesTable">
    <thead>
    <th>GGL Version</th>
    <th>Description</th>
    <th>Create Date</th>
    </thead>
    <c:forEach var="rv" items="${releaseVersions}">
        <tr id="${rv.description}">
            <spring:url value="/release-management/{versionNumber}" var="rvUrl">
                <spring:param name="versionNumber" value="${rv.version}"/>
            </spring:url>
            <td class="rvVersion"><a href="${fn:escapeXml(rvUrl)}">${rv.version}</a></td>
            <td class="rvDescription"><a href="${fn:escapeXml(rvUrl)}">${rv.description}</a></td>
            <td>${rv.createDate}</td>
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

