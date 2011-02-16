<%@ include file="/WEB-INF/jsp/include/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>

<h2>Counterparties</h2>

<p/>

<h3>Counterparties Information</h3>
<ul>
    <c:forEach items="${counterparties}" var="cp">
        <li>${cp}</li>
    </c:forEach>
</ul>

<table>
    <thead>
    </thead>
</table>


<%@ include file="/WEB-INF/jsp/include/footer.jsp" %>






