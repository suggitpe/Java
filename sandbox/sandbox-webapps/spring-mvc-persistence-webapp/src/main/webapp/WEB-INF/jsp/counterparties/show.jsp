<%@ include file="/WEB-INF/jsp/include/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>

<h2>Counterparty Detail: ${counterparty.counterpartyLegalName}</h2>

<p></p>

<table>
    <tr>
        <th>ID</th>
        <td>${counterparty.id}</td>
    </tr>
    <tr>
        <th>External ID</th>
        <td>${counterparty.externalId}</td>
    </tr>
    <tr>
        <th>Legal Name</th>
        <td>${counterparty.counterpartyLegalName}</td>
    </tr>
    <tr>
        <th>Friendly Name</th>
        <td>${counterparty.counterpartyName}</td>
    </tr>
</table>
<table class="table-buttons">
    <tr>
        <td colspan="2" align="centre">
            <spring:url value="{cpId}/edit" var="editUrl">
                <spring:param name="cpId" value="${counterparty.id}"/>
            </spring:url>
            <a href="${fn:escapeXml(editUrl)}">Edit Counterparty</a>
        </td>
    </tr>
</table>

<h2>Contacts</h2>

<table>
    <thead>
    <th>Name</th>
    <th>Address</th>
    <th>Postcode</th>
    <th>Telephone</th>
    <th>Actions</th>
    </thead>
    <c:forEach var="contact" items="${counterparty.counterpartyContacts}">
        <tr>
            <td>${contact.contactFirstName} ${contact.contactLastName}</td>
            <td>${contact.contactAddress}</td>
            <td>${contact.contactPostcode}</td>
            <td>${contact.contactTelephone}</td>
            <td>
                <spring:url var="editUrl" value="{cpId}/contacts/{contactId}/edit">
                    <spring:param name="cpId" value="${counterparty.id}"/>
                    <spring:param name="contactId" value="${contact.id}"/>
                </spring:url>
                <a href="${fn:escapeXml(editUrl)}">Edit</a>
            </td>
        </tr>
    </c:forEach>
</table>
<table class="table-buttons">
    <tr>
        <td>
            <spring:url value="{cpId}/contacts/new" var="addUrl">
                <spring:param name="cpId" value="${counterparty.id}"/>
            </spring:url>
            <a href="${fn:escapeXml(addUrl)}">Add New Contact</a>
        </td>
    </tr>
</table>


<%@ include file="/WEB-INF/jsp/include/footer.jsp" %>