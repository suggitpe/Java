<%@ include file="/WEB-INF/jsp/include/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>


<h2><fmt:message key="welcome"/></h2>

<ul>
    <li><a href="<spring:url value="ping" htmlEscape="true" />">Ping Test</a></li>
    <li><a href="<spring:url value="counterparties" htmlEscape="true" />">Counterparties</a></li>
</ul>

<p>&nbsp;</p>

<p>&nbsp;</p>

<%@ include file="/WEB-INF/jsp/include/footer.jsp" %>
