<%
    String format = "yyyy/MM/dd";
    String language = request.getHeader("accept-language");
    System.out.println(format + " + " + language);
    if( language != null )
    {
        if( language.toUpperCase().indexOf("EN-US") != -1)
        {
            format = "MM/dd/yyyy";
        }
        else if( language.toUpperCase().indexOf("EN-UK") != -1 || language.toUpperCase().indexOf("EN-GB") != -1 )
        {
            format = "dd/MM/yyyy";
        }
    }
    request.setAttribute("format", format);
%>
<%@ taglib uri="http://www.suggs.org.uk/test/example" prefix="ex" %>
<%@ taglib uri="http://www.suggs.org.uk/format/format" prefix="format" %>
<html>
    <head>
        <title>Tag Test</title>
        <format:includeFormat/>
    </head>
    <body>
        <h3>Basic custom tag test</h3>
        
        <hr/>
        Test for a simple tag:<br/>
        <ex:testFooTag/>
        
        <hr/>
        Test for a date formatting tag:
        <ul>
            <li>Test with attribute: <ex:formatDate format="dd-MM-yyyy"/> </li>
            <li>Test without attribute: <ex:formatDate/> </li>
        </ul>
        
        <hr/>
        Test for a dynamic attribute:<br/>
        <ex:dynamicAttribute name="attName" value="attValue" foo="attBar"/>
        
        <hr/>
        Test for the correctly formatted date [${format}]:<br/>
        <ex:formatDate format="${format}"/>
        
        <hr/>
        Test for passing the tag body through:<br/>
        <ex:testFooBodyTag>
            Hello tag body world!
        </ex:testFooBodyTag>
        
    </body>
</html>