<%@ page import="java.util.logging.*" %>
<%
Logger logger = Logger.getLogger("Example");
logger.addHandler( new ConsoleHandler() );

String info = request.getParameter("info");
if( info != null && !info.equals(""))
{
    logger.info(info);
}
%>
<html>
    <head>
        <title>Simple logging example</title>
        <link rel="stylesheet" type="text/css" href="/format.css"/>
    </head>
    <body>
        <h3>Please enter some information to log</h3>
        <hr/>
        <form>
            Information to log: <input name="info"/><br/>
            <input type="submit"/>
        </form>
        <hr/>
    </body>
</html>