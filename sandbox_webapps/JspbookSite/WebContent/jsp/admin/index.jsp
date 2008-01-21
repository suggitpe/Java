<%@ taglib uri="http://www.suggs.org.uk/format/format" prefix="format"%>
<html>
    <head>
        <title>Admin Index Page</title>
        <format:includeFormat />
    </head>
    <body>
    
        <h2>Add</h2>
        <a href="addNews.jsp">Add News</a><br>
        <a href="addFaq.jsp">Add FAQ</a><br>
        <a href="addErrata.jsp">Add Errata</a><br>
        <a href="addFeedback.jsp">Add Feedback</a><br>

        <h2>Edit</h2>
        <a href="edit.jsp?table=news">Edit News</a><br>
        <a href="edit.jsp?table=faq">Edit FAQ</a><br>
        <a href="edit.jsp?table=errata">Edit Errata</a><br>
        <a href="edit.jsp?table=feedback">Edit Feedback</a><br>

        <h2><a href="arbitrarySql.jsp">SQL Interface to DB</a></h2>
        <br>
        <a href="../../index.jsp">Return to Index.</a>
        
    </body>
</html>
