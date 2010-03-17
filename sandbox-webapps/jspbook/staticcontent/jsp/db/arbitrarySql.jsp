<%@ taglib uri="http://www.suggs.org.uk/format/format" prefix="format"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.Statement"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.naming.InitialContext"%>
<%
    Statement stmt = null;

    InitialContext initCtx = new InitialContext();
    Context ctx = (Context)initCtx.lookup("java:comp/env");
    DataSource src = (DataSource)ctx.lookup("jdbc/localdb");
    
    
    Connection conn = src.getConnection();
    
    try
    {
        stmt = conn.createStatement();
%>
<html>
    <head>
        <title>Arbitrary SQL</title>
        <format:includeFormat />
    </head>
    <body>
        <h3>Arbitrary SQL</h3>
        <hr/>
<%  // poor form model 1
    String query = request.getParameter("query");
    if( query != null && !query.equals("") )
    {
        ResultSet rs = stmt.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        
        out.println("<h5>Results of the query:</h5>");
        out.println(query);
        
        out.println("<br/><table border=\"1\"><tr>");
        for( int i = 0; i < rsmd.getColumnCount(); ++i )
        {
            out.println("<td>"+rsmd.getColumnName(i+1)+"</td>");
        }
        out.println("</tr>");
        
        while( rs.next() )
        {
            out.println("<tr>");
            for( int i = 0; i < rsmd.getColumnCount(); ++i)
            {
                out.println("<td>"+rs.getString(i+1)+"</td>");
            }
            out.println("</tr>");
        }
        out.println("</table>");
        
    }
%>
        <h5>New Query</h5>
        <form method="POST">
            <table>
                <tr>
                    <td>Query:</td>
                    <td><textarea name="query" cols="65"></textarea></td>
                </tr>
            </table>
            <input type="submit"/>
        </form>
<%
    }
    catch( Exception e )
    {
        out.println("<h5>Problem with query</h5>");
        out.println(e.getMessage());
    }
    finally
    {
        conn.close();
    }
%>
    </body>
</html>
