<%@ taglib uri="http://www.suggs.org.uk/format/format" prefix="format" %>
<html>
    <head>
        <title>JspBook Login</title>
        <format:includeFormat/>
    </head>
    <body>
        <h3>JspBook Security login page</h3>
        <hr/>
        <form method="POST" action="j_security_check">
            <table>
                <tr>
                    <td>User name:</td>
                    <td><input type="text" name="j_username"/></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="j_password"/></td>
                </tr>
            </table>
            <input type="submit" value="Logon"/>
        </form>
    </body>
</html>
