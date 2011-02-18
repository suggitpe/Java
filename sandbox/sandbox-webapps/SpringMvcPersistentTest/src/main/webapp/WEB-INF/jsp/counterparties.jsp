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
        <tr>
            <td>
                <spring:url value="/counterparties/{counterpartyId}" var="cpUrl">
                    <spring:param name="counterpartyId" value="${cp.id}"/>
                </spring:url>
                <a href="${fn:escapeXml(cpUrl)}">${cp.counterpartyLegalName}</a>
            </td>
            <td>${cp.counterpartyName}</td>
            <td>${cp.externalId}</td>
            <td>
                ${fn:length(cp.counterpartyContacts)}
            </td>
        </tr>

    </c:forEach>
</table>
<table class="table-buttons">
    <tr>
        <td colspan="2" align="centre">
            <spring:url value="/counterparties/new" var="addUrl"/>
            <a href="${fn:escapeXml(addUrl)}">Add Counterparty</a>
        </td>
    </tr>
</table>


<%@ include file="/WEB-INF/jsp/include/footer.jsp" %>






