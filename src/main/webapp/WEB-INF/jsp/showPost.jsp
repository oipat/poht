<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="row">
    <p class="offset">
        <h2 class="span10">
            ${postToShow.title}
        </h2>
    </p>
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
        <a href="/blogi/postform/${postToShow.id}">Edit</a>
        <a href="/blogi/postDelete/${postToShow.id}">Delete</a>
    </p>
</div>
</sec:authorize>
