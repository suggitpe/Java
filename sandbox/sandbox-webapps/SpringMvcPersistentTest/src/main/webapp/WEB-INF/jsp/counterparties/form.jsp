<%@ include file="/WEB-INF/jsp/include/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>

<c:choose>
    <c:when test="${counterparty.new}"><c:set var="method" value="post"/></c:when>
    <c:otherwise><c:set var="method" value="post"/></c:otherwise>
</c:choose>

<h2><c:if test="${counterparty.new}">New </c:if>Counterparty:</h2>

<form:form modelAttribute="counterparty" method="${method}">
    <table>
        <tr>
            <th>
                Name: <form:errors path="counterpartyName" ccsClass="errors"/>
                <br/>
                <form:input path="counterpartyName" size="30" maxlength="80"/>
            </th>
        </tr>
        <tr>
            <th>
                Legal Name: <form:errors path="counterpartyLegalName" ccsClass="errors"/>
                <br/>
                <form:input path="counterpartyLegalName" size="30" maxlength="80"/>
            </th>
        </tr>
        <tr>
            <th>
                External ID: <form:errors path="externalId" ccsClass="errors"/>
                <br/>
                <form:input path="externalId" size="30" maxlength="10"/>
            </th>
        </tr>
        <tr>
            <td>
                <c:choose>
                    <c:when test="${counterparty.new}">
                        <p class="submit"><input type="submit" value="Add Counterparty"/></p>
                    </c:when>
                    <c:otherwise>
                        <p class="submit"><input type="submit" value="Update Counterparty"/></p>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>
</form:form>

<c:if test="${!counterparty.new}">
    <form:form method="delete">
        <p class="submit"><input type="submit" value="Delete Counterparty"/></p>
    </form:form>
</c:if>


<%@ include file="/WEB-INF/jsp/include/footer.jsp" %>