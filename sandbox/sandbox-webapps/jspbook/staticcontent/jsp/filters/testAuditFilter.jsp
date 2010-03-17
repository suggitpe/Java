<%@ taglib uri="http://www.suggs.org.uk/format/format" prefix="format" %>
<html>
    <head>
        <title>Audit Filter Test Page</title>
        <format:includeFormat/>
    </head>
    <body>
        <h3>Test page for the audit filter</h3>
        <%
            request.getContentLength();
            request.getHeader("Host");
        %>
    </body>
<html>