<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<html>
    <head>
        <title>JSTL basics</title>
        <link rel="stylesheet" type="text/css" href="/format.css"/>
    </head>
    <body>
        <h3>The JSLT basics</h3>
        <ul>
            <li>The &lt;c:out&gt; tag can be used to create standard out commands: [<c:out value="${'<tag>, &'}"/>]</li>
            <li>The &lt;c:forEach&gt; tag can be used to iterate over a collection of objects:
                <ol>
                    <c:forEach var="entry" items="${header}" >
                        <li>The value is [${entry.value}]</li>
                    </c:forEach>
                </ol>
            </li>
            <li>The &lt;c:forTokens&gt; tag can be used to iterate over a tokenised string (eg for string "hello-this-is-a-tokenised-string"):
                <ol>
                    <c:forTokens items="hello-this-is-a-tokenised-string" delims="-" var="token">
                        <li>This is the value [${token}]</li>
                    </c:forTokens>
                </ol>
            </li>
            <li>The &lt;c:if&gt; tag allows for conditional logic: 
                [<c:if test="true">true</c:if><c:if test="false">false</c:if>]</li>
            <li>The &lt;c:choose&gt; tag allows for an else clause to the &lt;c:if&gt; tag:
                [<c:choose>
                    <c:when test="false">false</c:when>
                    <c:otherwise>true</c:otherwise>
                </c:choose>]</li>
        </ul>
    </body>
</html>
