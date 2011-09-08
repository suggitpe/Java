<%@ include file="/WEB-INF/jsp/includes/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/includes/header.jsp" %>

<h2 id="title">Release Version Summary</h2>

<table>
    <tr>
        <th>Release Version</th>
        <td id="releaseVersion">${releaseVersion.version}</td>
    </tr>
    <tr>
        <th>Description</th>
        <td id="description">${releaseVersion.description}</td>
    </tr>
    <tr>
        <th>Create Date</th>
        <td>${releaseVersion.createDate}</td>
    </tr>
    <c:forEach items="${releaseVersion.componentVersions}" var="componentVersions">
        <tr>
            <th>${componentVersions.key}</th>
            <td id="${componentVersions.key}Version">${componentVersions.value}</td>
        </tr>
    </c:forEach>
</table>
<table class="table-buttons">
    <tr>
        <td colspan="2" align="centre">
            <spring:url value="{rvId}/edit" var="editUrl">
                <spring:param name="rvId" value="${releaseVersion.version}"/>
            </spring:url>
            <a id="editReleaseVersionLink" href="${fn:escapeXml(editUrl)}">Edit version</a>
        </td>
    </tr>
</table>


<%@ include file="/WEB-INF/jsp/includes/footer.jsp" %>

