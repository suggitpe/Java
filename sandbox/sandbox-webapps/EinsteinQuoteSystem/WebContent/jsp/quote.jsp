<%@page import="java.util.Random"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
    String[] quotes = {
                       "Before God we are all equally wise - and equally foolish",
                       "I never think of the future - it comes soon enough",
                       "Imagination is more important than knowledge...",
                       "Reality is merely an illusion, albeit a very persistent one",
                       "The important thing is not to stop questioning",
                       "The secret to creativity is knowing how to hide your sources",
                       "Science without religion is lame, religion without science is blind",
                       "Everything that is really great and inspiring is created by"
                                       + "the individual who can labour in freedom" };
    List<String> l = new ArrayList<String>( Arrays.asList( quotes ) );
    Random r = new Random();
    int x = r.nextInt( l.size() );
    String saying = l.get(x);
    saying += " -- Albert Einstein";
    pageContext.setAttribute("saying", saying );
%>
<html>
    <head>
        <title>JBoss quote page</title>
    </head>
    <body>
        <br/>
        <c:set var="sessionCount" scope="session" value="${sessiomnCount + 1}"/>
        <c:set var="applicationCount" scope="application" value="${applicationnCount + 1}"/>
        <h3><font color="#1230cb">The Albert Einstein quote machine</font></h3>
        <h5>
            <font color="#a6a6a6">${saying}</font>
        </h5>
        <hr/>
        <p>You've visited this webapp ${sessionCount} times this session and 
        by other people ${applicationCount} times</p>
    </body>
</html>
