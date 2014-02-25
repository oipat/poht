<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Le post</h1>

<form:form cssClass="form-stacked" commandName="post" method="${method}" action="${pageContext.request.contextPath}/post/${post.id}">

    <form:label path="title">Title</form:label>
    <form:input path="title" />
    <form:errors path="title" cssClass="error" />
    <!-- laiskana br, parantelun varaa -->
    <br /><br />
    <form:errors path="body" cssClass="error"/>
    <form:textarea cssStyle="" path="body" />

    <br /><br />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="Submit"/>
</form:form>