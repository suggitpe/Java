<%@ include file="header.jsp"%>
<!-- 
    The above is the same as below .. except that the jsp:include
        is dynamically compiles ... the include directive is compiled only at 
        translation time
<jsp:include page="header.jsp"/>
-->
<hr />
Only the content of the page is unique.
<br />
You should consider whether you want to include the files using the
include directive, or to include through the JSP config in the web.xml
<pre>
 &lt;jsp-config&gt;
    &lt;jsp-property-group&gt;
        &lt;url-pattern&gt;*.jsp&lt;/url-pattern&gt;
        &lt;include-preclude&gt;header.jsp&lt;/include-preclude&gt;
        &lt;include-coda&gt;footer.jsp&lt;/include-coda&gt;
    &lt;/jsp-property-group&gt;
 &lt;/jsp-config&gt;
</pre>
If you use the jsp:include method you will guarantee that any dynamic
changes done to the include file are reflected in the calling page.
<br />
However, if you use the include directive (either through the web.xml or
in the jsp page) the changes will not be reflected until the main page
is recompiled. This is because the include directive includes the code
prior to the compile and the jsp:include includes the results of the
compilation.
<hr />
<%@ include file="footer.jsp"%>