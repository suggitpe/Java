<%@ include file="/WEB-INF/jsp/include/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>

<h2>Counterparties</h2>

<p/>

<h3>Counterparties Information</h3>

<table>
    <thead>
    <th>Legal name</th>
    <th>Name</th>
    <th>External ID</th>
    <th>Contacts</th>
    </thead>
    <c:forEach var="cp" items="${counterparties}">
        <td>
            <spring:url value="counterparties/counterpartyId" var="cpUrl">
                <spring:param name="counterpartyId" value="${cp.id}"/>
            </spring:url>
            <a href="${fn:escapeXml(cpUrl)}">${cp.counterpartyLegalName}</a>
        </td>
        <td>${cp.counterpartyName}</td>
        <td>${cp.externalId}</td>
        <td>
            <c:forEach var="contact" items="${cp.counterpartyContacts}">
                ${contact.firstName} &nbsp;
            </c:forEach>
        </td>
    </c:forEach>
</table>


<%@ include file="/WEB-INF/jsp/include/footer.jsp" %>






