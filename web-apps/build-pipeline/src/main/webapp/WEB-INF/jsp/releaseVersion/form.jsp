<%@ include file="/WEB-INF/jsp/includes/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/includes/header.jsp" %>

<c:choose>
    <c:when test="${releaseVersion.new}"><c:set var="method" value="post"/></c:when>
    <c:otherwise><c:set var="method" value="post"/></c:otherwise>
</c:choose>

<h2 id="title"><c:if test="${releaseVersion.new}">New </c:if> <c:if test="${!releaseVersion.new}">Edit </c:if>Release Version</h2>

<table>
    <tr>
        <td>
            <form:form modelAttribute="releaseVersion" method="${method}">
                <table>
                    <c:forEach items="${componentVersionsBean.components}" var="componentBean">
                        <tr>
                            <td>${componentBean.componentName} version: <form:errors path="componentVersions[${componentBean}]"/></td>
                            <td>
                                <form:select path="componentVersions[${componentBean.componentName}]">
                                    <form:option value="NONE" label="--- Select ---"/>
                                    <form:options items="${componentVersionsBean.componentVersions[componentBean]}"/>
                                </form:select>
                            </td>
                        </tr>
                    </c:forEach>

                    <tr>
                        <td>
                            Description: <form:errors path="description" ccsClass="errors"/>
                        </td>
                        <td>
                            <form:input id="descriptionField" path="description" size="30" maxlength="180"/>
                        </td>
                    </tr>

                    <c:forEach items="${componentVersionsBean.testSuites}" var="testSuiteBean">
                        <tr>
                            <td>${testSuiteBean.componentName} version: <form:errors path="componentVersions[${testSuiteBean}]"/></td>
                            <td>
                                <form:select path="componentVersions[${testSuiteBean.componentName}]">
                                    <form:option value="NONE" label="--- Select ---"/>
                                    <form:options items="${componentVersionsBean.componentVersions[testSuiteBean]}"/>
                                </form:select>
                            </td>
                        </tr>
                    </c:forEach>

                    <tr>
                        <td>
                            <c:choose>
                                <c:when test="${releaseVersion.new}">
                                    <p class="submit"><input id="addButton" type="submit"
                                                             value="Add New Release Version"/>
                                    </p>
                                </c:when>
                                <c:otherwise>
                                    <p class="submit"><input id="updateButton" type="submit"
                                                             value="Update Release Version"/></p>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </table>
            </form:form>
        </td>
    </tr>
    <tr>
        <td>
            <c:if test="${!releaseVersion.new}">
                <form:form method="delete">
                    <p class="submit"><input id="deleteButton" type="submit" value="Delete Release Version"/></p>
                </form:form>
            </c:if>
        </td>
    </tr>
</table>

<%@ include file="/WEB-INF/jsp/includes/footer.jsp" %>
