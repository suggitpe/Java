<%@ taglib uri="http://www.suggs.org.uk/format/format" prefix="format" %>
<html>
    <head>
        <title>Show IP Address</title>
        <format:includeFormat/>
    </head>
    <body>
        <h3>Show IP Address</h3>
        <hr/>
        Calling address is [<%= request.getRemoteAddr() %>]
    </body>
</html>
