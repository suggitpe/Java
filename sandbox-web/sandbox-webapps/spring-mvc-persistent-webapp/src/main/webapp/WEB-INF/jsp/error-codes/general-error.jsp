<%@ page import="java.io.PrintWriter" %>
<%@ include file="/WEB-INF/jsp/include/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>

<%
    Exception exception = ( Exception ) request.getAttribute( "exception" );
%>

<h1>General Error: <%= exception.getMessage() %>
</h1>

<%
    exception.printStackTrace( new PrintWriter( out ) );
%>

<%@ include file="/WEB-INF/jsp/include/footer.jsp" %>