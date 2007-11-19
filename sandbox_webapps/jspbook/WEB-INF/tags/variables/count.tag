<!-- This tag will create a varible in the page that can be selected by the internal JSP page.  The 
variable that this creates is one called pageCount -->
<%@ variable name-given="pageCount" variable-class="java.lang.Integer" scope="AT_BEGIN" %>
<%
    Integer pageCount =(Integer)application.getAttribute("pageCount");
    if (pageCount == null)
    {
        application.setAttribute("pageCount", new java.lang.Integer(1));
    }
    else
    {
        application.setAttribute("pageCount", new java.lang.Integer( ((Integer)application.getAttribute("pageCount")) + 1 ));
    }
%>