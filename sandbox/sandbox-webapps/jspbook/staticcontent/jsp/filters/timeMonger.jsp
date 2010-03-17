<%@ taglib uri="http://www.suggs.org.uk/format/format" prefix="format" %>
<html>
    <head>
        <title>Time Monger Test Page</title>
        <format:includeFormat/>
    </head>
    <body>
        <h3>A test for the cache filter</h3>
        <hr/>
        <%
            // mock time consuming code
            for( int i = 0; i < 100000; ++i )
            {
                for( int j=0; j < 1000; ++j )
                {
                    // nadda
                }
            }
        %>
    </body>
<html>