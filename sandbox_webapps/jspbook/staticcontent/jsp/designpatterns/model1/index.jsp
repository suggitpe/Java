<%@page import="java.io.File"%>
<%@page import="javax.xml.parsers.DocumentBuilderFactory"%>
<%@page import="org.w3c.dom.Document"%>
<%@page import="javax.xml.parsers.DocumentBuilder"%>
<%@page import="org.w3c.dom.NodeList"%>
<%@page import="org.w3c.dom.Element"%>
<jsp:include page="header.jsp"/>
<%
    ServletContext ctx = pageContext.getServletContext();
    String dir = ctx.getRealPath("/staticcontent/jsp/designpatterns/model1");
    File file = new File(dir + "/news.xml");
    DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = fact.newDocumentBuilder();
    Document doc = null;
    
    // if the file already exists then we are OK
    if( file.exists() ) 
    {
        doc = builder.parse(file);
    }
    
    if( doc != null ) 
    {
        NodeList list = doc.getElementsByTagName("story");
        for( int i = 0; i < list.getLength(); ++i )
        {
            Element e = (Element) list.item(i);
%>
<hr/>
<h3><a href="<%= e.getAttribute("link") %>"><%= e.getAttribute("title") %></a></h3> <%= e.getAttribute("story") %>
<%
        }
    }
%>
<jsp:include page="footer.jsp"/>
