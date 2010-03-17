<%@ taglib uri="http://www.suggs.org.uk/format/format" prefix="format" %>
<html>
    <head>
        <title>Encode URL</title>
        <format:includeFormat/>
    </head>
    <body>
        <h3>Encode URL</h3>
        
        <hr/>
        A local URL: index.html<br/>
        <%= response.encodeURL("index.jsp") %><br/>
        <%= response.encodeRedirectURL("index.jsp") %><br/>
        
        <hr/>
        
        A remote URL: http://www.jspbook.com<br/>
        <%= response.encodeURL("http://www.jspbook.com") %><br/>
        <%= response.encodeRedirectURL("http://www.jspbook.com") %><br/>
        <hr/>
    </body>
</html>