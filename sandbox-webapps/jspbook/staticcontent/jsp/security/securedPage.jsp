<%@ taglib uri="http://www.suggs.org.uk/format/format" prefix="format" %>
<html>
    <head>
        <title>Success you are in</title>
        <format:includeFormat/>
    </head>
    <body>
        <h3>Success you are in</h3>
        <hr/>
        Calling address is [<%= request.getRemoteAddr() %>]
    </body>
</html>
