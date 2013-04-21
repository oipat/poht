<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Le post</h1>

<form:form cssClass="form-stacked" commandName="post">
    <form:errors path="*" cssClass="errorblock" element="div" />

    <form:label path="title">Title</form:label>
    <form:input path="title" />
    <form:errors path="title" cssClass="error" />
    <!-- laiskana br, parantelun varaa -->
    <br /><br />
    <form:textarea cssStyle="" path="body" />
    <form:errors path="body" cssClass="error" />

    <br /><br />
    <input type="submit" value="Submit"/>
</form:form>