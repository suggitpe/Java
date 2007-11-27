<%@page import="org.suggs.sandbox_webapps.filters.linktracker.Link"%>
<%@page import="org.suggs.sandbox_webapps.filters.linktracker.LinkTrackerFilter"%>
<%@ taglib uri="http://www.suggs.org.uk/format/format" prefix="format" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%
Link[] requests = LinkTrackerFilter.getRequests();
Link[] responses = LinkTrackerFilter.getResponses();
Link[] referers = LinkTrackerFilter.getReferers();
request.setAttribute("requests", requests);
request.setAttribute("responses", responses);
%>
<html>
    <head>
        <title>Link Tracker</title>
        <format:includeFormat/>
    </head>
    <body>
        <h3>Link Tracker display</h3>
        <hr/>
        
        <h5>Requests</h5>
        <p class="nopad">
            <c:forEach var="r" begin="0" items="${requests}">
                url=[<a href="${r.url} }">${r.url}</a>] count=[${r.count}] ${r.lastVisited}<br/>
            </c:forEach>
        </p>
        <hr/>
        
        <h5>Responses</h5>
        <p class="nopad">
            <c:forEach var="r" begin="0" items="${responses}">
                url=[<a href="${r.url} }">${r.url}</a>] count=[${r.count}] ${r.lastVisited}
            </c:forEach>
        </p>
        
        <hr/>
        <h5>Refrers</h5>
        <p class="nopad">
            <c:forEach var="r" begin="0" items="${referers}">
                url=[<a href="${r.url} }">${r.url}</a>] count=[${r.count}] ${r.lastVisited}
            </c:forEach>
        </p>
        <hr/>
        
    </body>
</html>
