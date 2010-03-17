<%@ taglib uri="http://www.suggs.org.uk/format/format" prefix="format" %>
<html>
    <head>
        <title>Show Session</title>
        <format:includeFormat/>
    </head>
    <body>
        <h3>Show Session</h3>
        <hr/>
        Session ID: <%= session.getId() %>
        <br/>
        Session isNew: <%= session.isNew() %>
    </body>
</html>
