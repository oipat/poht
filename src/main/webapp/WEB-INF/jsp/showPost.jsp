<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <h2 class="span10">
        ${postToShow.title}
    </h2>
</div>
<div class="row">
    <p class="span10">
        by
        ${postToShow.author.firstName}
        ${postToShow.author.surName}
        @ ${postToShow.created}
    </p>
</div>
<div class="row">
    <p class="span10">
        ${postToShow.body}
    </p>
</div>
<sec:authorize access="hasRole('ROLE_USER')">
    <div class="row">
        <p class="span10">
            <a href="${pageContext.request.contextPath}/postform/${postToShow.id}">Edit</a>
            <a href="${pageContext.request.contextPath}/postDelete/${postToShow.id}">Delete</a>
        </p>
    </div>
</sec:authorize>

<c:forEach items="${postToShow.comments}" var="aComment"> 
    <div class="row">
        <h3 class="span10">${aComment.author}</h3>
    </div>
    <div class="row">
        <p class="span10">${aComment.body}</p>
    </div>
    <sec:authorize access="hasRole('ROLE_USER')">
        <div class="row">
            <p class="span10">
                <a href="${pageContext.request.contextPath}/commentDelete/${aComment.id}">Delete</a>
            </p>
        </div>
    </sec:authorize>
</c:forEach>


<div class="row">
    <h3 class="span10">Comment</h3>
    <form:form cssClass="form-stacked" commandName="comment" method="post" action="${pageContext.request.contextPath}/comment/${postToShow.id}">

        <form:label path="author">Name</form:label>
        <form:input path="author" />
        <form:errors path="author" cssClass="error" />
        <!-- laiskana br, parantelun varaa -->
        <br /><br />
        <form:errors path="body" cssClass="error"/>
        <form:textarea cssClass="comment" path="body" />

        <br /><br />
        <input type="submit" value="Submit"/>
    </form:form>
</div>

