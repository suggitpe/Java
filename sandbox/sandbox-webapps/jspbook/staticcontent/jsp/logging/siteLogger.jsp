<%@page import="java.util.logging.Level"%>
<%@page import="org.suggs.sandbox_webapps.logging.logger.SiteLogger"%>
<%@page import="java.util.logging.Logger"%>
<%
    Logger logger = SiteLogger.getLogger();

    String info = request.getParameter( "info" );
    String level = request.getParameter( "level" );

    if ( info != null && !info.equals( "" ) && level != null && !level.equals( "" ) )
    {
        logger.log( Level.parse( level ), info );
    }
%>
<html>
    <head>
        <title>A simple logger</title>
        <link rel="stylesheet" type="text/css" href="/format.css"/>
    </head>
    <body>
        <form>
            <table>
                <tr>
                    <td>Level:</td>
                    <td>
                        <select name="level">
                            <option value="SEVERE">Severe</option>
                            <option value="WARNING">Warning</option>
                            <option value="INFO">Info</option>
                            <option value="CONFIG">Config</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Information to log:</td>
                    <td><input name="info"/></td>
                </tr>
            </table>
            <input type="submit"/>    
        </form>
    </body>
</html>
