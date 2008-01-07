<jsp:include page="header.jsp"/>

<h3>Add News</h3>
<p>Fill in all fields to add your news to Foo news.</p>
<form method="post">
    <table>
        <tr>
            <td>Title:</td>
            <td><input size="50" name="title" value="${form.title}"/></td>
        </tr>
        <tr>
            <td>Link:</td>
            <td><input size="50" name="link" value="${form.link}"/></td>
        </tr>
        <tr>
            <td>Story:</td>
            <td><textarea cols="50" rows="10" name="story">${form.story}</textarea></td>
        </tr>
    </table>
    <input type="submit" value="Add News"/>
</form>

<jsp:include page="footer.jsp"/>