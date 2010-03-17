<%@ taglib uri="http://www.suggs.org.uk/format/format" prefix="format"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%
    InitialContext initCtx = new InitialContext();
    Context ctx = (Context)initCtx.lookup("java:comp/env");
    DataSource src = (DataSource)ctx.lookup("jdbc/localdb");
 
    Connection conn = src.getConnection();
    StringBuffer errBuff = new StringBuffer();
    
    try
    {
        Statement s = conn.createStatement();

%>
<html>
    <head>
        <title>Show Database Tables</title>
        <format:includeFormat />
    </head>
    <body>
        <h3>Show Database Tables</h3>
        <hr/>
        
        <h5>Content of LINK table</h5>
        <table border="1">
        <tr>
            <td>URL</td>
            <td>Title</td>
            <td>Description</td>
        </tr>
<%
    ResultSet set1 = s.executeQuery("select * from link");
    while( set1.next() )
    {
        out.println("<tr>");
        out.println("<td>"+set1.getString("url")+"</td>");
        out.println("<td>"+set1.getString("title")+"</td>");
        out.println("<td>"+set1.getString("description")+"</td>");
        out.println("</tr>");
    }
%>
        </table>
        <hr/>
        
        <h5>Content of URI table</h5>
        <table border="1">
            <tr>
                <td>URI</td>
                <td>URL</td>
            </tr>
<%
    ResultSet set2 = s.executeQuery("select * from uri");
    while( set2.next() )
    {
        out.println("<tr>");
        out.println("<td>" + set2.getString("uri") + "</td>");
        out.println("<td>" + set2.getString("url") + "</td>");
        out.println("</tr>");
    }
%>
        </table>
<%
    }
    catch( Exception e )
    {
        out.println("<h5>Problem with query</h5><br/>"+e.getMessage());
    }
    finally
    {
        conn.close();
    }
%>
    </body>
</html>
