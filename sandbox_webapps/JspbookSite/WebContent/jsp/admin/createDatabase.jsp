<%@ taglib uri="http://www.suggs.org.uk/format/format" prefix="format"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%
    StringBuffer errBuff = new StringBuffer();
    StringBuffer outBuff = new StringBuffer();

    InitialContext initCtx = new InitialContext();
    Context ctx = (Context)initCtx.lookup("java:comp/env");
    DataSource src = (DataSource)ctx.lookup("jdbc/localdb");
 
    Connection conn = src.getConnection();
    conn.setAutoCommit(false);
    
    try
    {
        Statement s = conn.createStatement();
        // drop existing tables
        List<String> tables = new ArrayList<String>();
        tables.add("NEWS");
        tables.add("FAQ");
        tables.add("FEEDBACK");
        tables.add("ERRATA");
        
        for( String t : tables )
        {
            StringBuffer dropLink = new StringBuffer("drop table ").append(t)
                    .append(" cascade constraints");
            try
            {
                s.executeQuery(dropLink.toString());
                outBuff.append("No problems dropping ").append(t).append("<br/>");
            }
            catch( SQLException e )
            {
                errBuff.append("Drop ").append(t).append(" [").append(e.getMessage()).append("]<br/>");
            }
        }
        
        // now build the tables
        // NEWS
        {
            StringBuffer create = new StringBuffer("create table NEWS ")
                .append("(")
                .append( "TS NUMBER(*,0) NOT NULL ENABLE, ")
                .append( "VISIBLE NUMBER(1,0)")
                .append(")");
            try
            {
                s.executeQuery(create.toString());
                outBuff.append("No problems creating NEWS<br/>");
            }
            catch( SQLException e )
            {
                errBuff.append("Create NEWS [").append(e.getMessage()).append("]<br/>");
            }
        }
        
        // FAQ
        {
            StringBuffer create = new StringBuffer("create table FAQ ")
                .append("(")
                .append( "TS NUMBER(*,0) NOT NULL ENABLE, ")
                .append( "VISIBLE NUMBER(1,0)")
                .append(")");
            try
            {
                s.executeQuery(create.toString());
                outBuff.append("No problems creating FAQ<br/>");
            }
            catch( SQLException e )
            {
                errBuff.append("Create FAQ [").append(e.getMessage()).append("]<br/>");
            }
        }
        
        // FEEDBACK
        {
            StringBuffer create = new StringBuffer("create table FEEDBACK ")
                .append("(")
                .append( "TS NUMBER(*,0) NOT NULL ENABLE, ")
                .append( "VISIBLE NUMBER(1,0)")
                .append(")");
            try
            {
                s.executeQuery(create.toString());
                outBuff.append("No problems creating FEEDBACK<br/>");
            }
            catch( SQLException e )
            {
                errBuff.append("Create FEEDBACK [").append(e.getMessage()).append("]<br/>");
            }
        }
        
        // ERRATA
        {
            StringBuffer create = new StringBuffer("create table ERRATA ")
                .append("(")
                .append( "TS NUMBER(*,0) NOT NULL ENABLE, ")
                .append( "VISIBLE NUMBER(1,0)")
                .append(")");
            try
            {
                s.executeQuery(create.toString());
                outBuff.append("No problems creating ERRATA<br/>");
            }
            catch( SQLException e )
            {
                errBuff.append("Create ERRATA [").append(e.getMessage()).append("]<br/>");
            }
        }
        
        // now we can populate some of the data
        
        
    }
    catch(SQLException se)
    {
        errBuff.append("******** Rolling back transaction");
        conn.rollback();
    }
    finally
    {
        conn.close();
    }
%>
<html>
    <head>
        <title>Create database</title>
        <format:includeFormat />
    </head>
    <body>
        <h3>Database creation</h3>
        <p>The database has been correctly created<br/>
        Output from DDL: <br/> <%= outBuff.toString() %></p>
        
        <hr/>
        <p>Caught exceptions:<br/> <%= errBuff.toString() %></p>
    </body>
</html>
