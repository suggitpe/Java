<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>


<img src="<spring:url value="/static/images/data-access-logo-medium.gif" htmlEscape="true" />" align="right"
     style="position:relative;right:30px;">

<h2><fmt:message key="welcome"/></h2>

<ul>
    <li><a href="<spring:url value="ping" htmlEscape="true" />">Ping Test</a></li>
</ul>

<p>&nbsp;</p>

<p>&nbsp;</p>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
