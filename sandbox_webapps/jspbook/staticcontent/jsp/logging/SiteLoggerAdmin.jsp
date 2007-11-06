<%@page import="org.suggs.sandbox_webapps.logging.logger.SiteLogger"%>
<%@ page import="java.util.logging.*,java.io.*"%>
<%
    // get the logger
    Logger logger = SiteLogger.getLogger();

    // get the request parameters
    String level = request.getParameter( "level" );
    if ( level != null && !level.equals( "" ) )
    {
        logger.setLevel( Level.parse( level ) );
    }

    // set current level
    request.setAttribute( "lvl", logger.getLevel() );
    
    // set the current log
    StringWriter sw = new StringWriter();
    request.setAttribute( "log", sw );

    // parse in the current log
    InputStream strm = application.getResourceAsStream( "log.txt" );
    if ( strm != null )
    {
        for ( int i = strm.read(); i != -1; i = strm.read() )
        {
            sw.write( (char) i );
        }
    }
    else
    {
        sw.write("Can't write the log file");
    }
%>
<html>
    <head>
        <title>Site logging configuration</title>
        <link rel="stylesheet" type="text/css" href="/format.css"/>
    </head>
    <body>
        <h3>Site Logging Configuration</h3>
        <hr/>
        <p>The current level is set to: <b>${lvl}</b><% request.getAttribute("1"); %></p>
        <form>
            <select name="level">
                <option value="SEVERE">Severe</option>
                <option value="WARNING">Warning</option>
                <option value="INFO">Info</option>
                <option value="CONFIG">Config</option>
            </select><br/>
            <input type="submit"value="Update Level"><br/>
        </form>
        
        <p>Current log file:</p>
        <pre>${log}</pre>
    </body>
</html>