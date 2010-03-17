<%@ taglib uri="http://www.suggs.org.uk/format/format" prefix="format"%>
<html>
    <head>
        <title>Add News</title>
        <format:includeFormat />
    </head>
    <body>
        <h3>Add News</h3>
        <form method="POST">
            <table>
                <tr>
                    <td>Title:</td>
                    <td><input name="title" size="50"/></td>
                </tr>
                <tr>
                    <td>Link:</td>
                    <td><input name="link" size="50"/></td>
                </tr>
                <tr>
                    <td>Text:</td>
                    <td><textarea name="text" cols="38"></textarea></td>
                </tr>
            </table>
            <input type="submit" value="Submit"/>
        </form>
    </body>
</html>
        
