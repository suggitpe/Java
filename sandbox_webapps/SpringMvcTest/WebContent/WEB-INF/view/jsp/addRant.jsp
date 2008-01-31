<%@include file="include/taglibs.jsp"%>
<html>
    <head>
        <title>Add Rant</title>
        <format:includeFormat/>
    </head>
    <body>
        <h2>Enter a rant ... </h2>
        <form:form method="POST" action="addRant.htm" commandName="rant">
            <table>
                <tr>
                    <td><spring:message code="field.state"/></td>
                    <td><form:input path="vehicle.state"/></td>
                    <td><form:errors path="vehicle.state" cssClass="error"/></td>
                </tr>
                <tr>
                    <td><spring:message code="field.plateNumber"/></td>
                    <td><form:input path="vehicle.plateNumber"/></td>
                    <td><form:errors path="vehicle.plateNumber" cssClass="error"/></td>
                </tr>
                <tr>
                    <td><spring:message code="field.rantText"/></td>
                    <td><form:textarea path="rantText" rows="5" cols="50"/></td>
                    <td><form:errors path="rantText" cssClass="error"/></td>
                </tr>
            </table>
            <input type="submit" value="Submit" />
        </form:form>
    </body>
</html>
