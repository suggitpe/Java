<%@ taglib uri="http://www.suggs.org.uk/format/format" prefix="format"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.Context"%>
<%
    StringBuffer errBuff = new StringBuffer();

    InitialContext initCtx = new InitialContext();
    Context ctx = (Context)initCtx.lookup("java:comp/env");
    DataSource src = (DataSource)ctx.lookup("jdbc/localdb");
 
    Connection conn = src.getConnection();
    
    try
    {
        Statement s = conn.createStatement();
        // drop link table
        StringBuffer dropLink = new StringBuffer("drop table LINK cascade constraints");
        try
        {
            s.executeQuery(dropLink.toString());
            errBuff.append("No problems dropping LINK<br/>");
        }
        catch( SQLException e )
        {
            errBuff.append("DropLink [").append(e.getMessage()).append("]<br/>");
        }
        
        // create link table
        StringBuffer createLink = new StringBuffer("create table LINK ")
            .append("(")
            .append( "URL          VARCHAR2(128) primary key, ")
            .append( "TITLE        VARCHAR2(128), ")
            .append( "DESCRIPTION  VARCHAR2(255) ")
            .append(")");
        try
        {
            s.executeQuery(createLink.toString());
            errBuff.append("No problems creating LINK<br/>");
        }
        catch( SQLException e )
        {
            errBuff.append("CreateLink [").append(e.getMessage()).append("]<br/>");
        }
        
        // drop uri table
        StringBuffer dropUri = new StringBuffer("drop table URI cascade constraints");
        try
        {
            s.executeQuery(dropUri.toString());
            errBuff.append("No problems dropping URI<br/>");
        }
        catch( SQLException e )
        {
            errBuff.append("DropUri [").append(e.getMessage()).append("]<br/>");
        }
        
        // create the uri table
        StringBuffer createUri = new StringBuffer("create table URI ")
            .append("(")
            .append( "URI VARCHAR2(40), ")
            .append( "URL VARCHAR2(128) ")
            .append(")");
        try
        {
            s.executeQuery(createUri.toString());
            errBuff.append("No problems creating URI<br/>");
        }
        catch( SQLException e )
        {
            errBuff.append("CreateUri [").append(e.getMessage()).append("]<br/>");
        }
        
    }
    finally
    {
        conn.close();
    }
%>
<%@page import="java.sql.DriverManager"%>
<html>
    <head>
        <title>Create database</title>
        <format:includeFormat />
    </head>
    <body>
        <h3>Database creation</h3>
        <p>The database has been correctly created</p>
        <hr/>
        <p>Caught exceptions:<br/> <%= errBuff.toString() %></p>
    </body>
</html>
