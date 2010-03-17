<%@ taglib uri="http://www.suggs.org.uk/format/format" prefix="format" %>
<%@ taglib prefix="tgtst" tagdir="/WEB-INF/tags/test" %>
<%@ taglib prefix="tgvar" tagdir="/WEB-INF/tags/variables" %>
<tgvar:count/>
<html>
    <head>
        <title>Tag file test</title>
        <format:includeFormat/>
    </head>
    <body>
        <h3>Basic tag file tests</h3>
        
        <hr/>
        A very simple tag file test:<br/>
        <tgtst:hello/>
        
        <hr/>
        This page has been visited <%= pageCount %> times;
        
    </body>
</html>
