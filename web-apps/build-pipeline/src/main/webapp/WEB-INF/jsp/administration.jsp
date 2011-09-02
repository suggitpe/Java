<%@ include file="/WEB-INF/jsp/includes/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/includes/header.jsp" %>

<h2 id="title">Administration</h2>

<div>
    <form:form action="administration/deleteAllVersions" method="get">
        <p class="submit"><input id="deleteAllVersionsButton" type="submit" value="Delete All Release Versions"/></p>
    </form:form>
</div>

<%@ include file="/WEB-INF/jsp/includes/footer.jsp" %>

