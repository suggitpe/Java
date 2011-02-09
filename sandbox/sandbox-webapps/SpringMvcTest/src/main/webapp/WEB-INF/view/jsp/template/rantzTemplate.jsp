<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.suggs.org.uk/format/format" prefix="format" %>
<%-- <tiles:importAttribute name="title" scope="request"/> --%>
<html>
    <head>
        <title><tiles:getAsString name="title"/> </title>
        <format:includeFormat/>
    </head>
    <body>
        <table width="100%" border="0">
            <tr>
                <td><tiles:insertDefinition name="header"/></td>
            </tr>
            <tr valign="top" align="left">
                <td><tiles:insertDefinition name="content"/></td>
            </tr>
            <tr>
                <td><tiles:insertAttribute name="footer"/></td>
            </tr>
        </table>
    </body>
</html>