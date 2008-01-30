<%@include file="include/taglibs.jsp"%>
<html>
    <head>
        <title>Add Rant</title>
    </head>
    <body>
        <h2>Enter a rant ... </h2>
        <form method="POST" action="addRant.htm">
            <table>
                <tr>
                    <td>State:</td>
                    <td><form:input path="rant.vehicle.state"/></td>
                </tr>
                <tr>
                    <td>Plate num:</td>
                    <td><input type="text" name="rant.vehicle.plateNumber"/></td>
                </tr>
                <tr>
                    <td>Rant:</td>
                    <td><textarea name="rant.rantText" rows="5" cols="50"></textarea></td>
                </tr>
            </table>
            <input type="submit" value="Submit" />
        </form>
    </body>
</html>
