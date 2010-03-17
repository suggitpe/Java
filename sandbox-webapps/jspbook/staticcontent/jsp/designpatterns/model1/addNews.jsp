<%@page import="java.io.File"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="javax.xml.parsers.DocumentBuilderFactory"%>
<%@page import="javax.xml.parsers.DocumentBuilder"%>
<%@page import="javax.xml.transform.TransformerFactory"%>
<%@page import="javax.xml.transform.dom.DOMSource"%>
<%@page import="javax.xml.transform.stream.StreamResult"%>
<%@page import="javax.xml.transform.Transformer"%>
<%@page import="org.w3c.dom.Document"%>
<%@page import="org.w3c.dom.Element"%>
<jsp:include page="header.jsp" />
<%
    String title = request.getParameter( "title" );
    String link = request.getParameter( "link" );
    String story = request.getParameter( "story" );

    if ( title != null && !title.equals( "" ) && link != null && !link.equals( "" )
         && story != null && !story.equals( "" ) )
    {
        ServletContext ctx = pageContext.getServletContext();
        String dir = ctx.getRealPath( "/staticcontent/jsp/designpatterns/model1" );
        File file = new File( dir + "/news.xml" );

        DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = fact.newDocumentBuilder();
        Document doc = null;

        if ( file.exists() )
        {
            doc = builder.parse( file );
        }
        else
        {
            doc = builder.newDocument();
            Element root = doc.createElement( "news" );
            doc.appendChild( root );
        }

        Element news = doc.createElement( "story" );
        news.setAttribute( "title", title );
        news.setAttribute( "link", link );
        news.setAttribute( "story", story );
        doc.getDocumentElement().appendChild( news );

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        DOMSource source = new DOMSource( doc );
        StreamResult result = new StreamResult( new FileOutputStream( file ) );
        t.transform( source, result );
%>
<p align="center">Your news has been added!<br />
<a href="index.jsp">Back to main page.</a></p>
<%
    }
    else
    {
        if ( title == null )
        {
            title = "";
        }

        if ( link == null )
        {
            link = "";
        }

        if ( story == null )
        {
            story = "";
        }
%>
<h3>Add News</h3>
<p>Fill in all of the fields to add your news to Foo news.</p>
<form method="post">
<table>
    <tr>
        <td>Title:</td>
        <td><input size="50" name="title" value="<%= title %>" /></td>
    </tr>
    <tr>
        <td>Link:</td>
        <td><input size="50" name="link" value="<%= link %>" /></td>
    </tr>
    <tr>
        <td>Story:</td>
        <td><textarea cols="50" rows="10" name="story"><%=story%></textarea></td>
    </tr>
</table>
<input type="submit" value="Add News" /></form>
<%
    }
%>
<jsp:include page="footer.jsp" />
