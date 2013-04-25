<!--

leiska kopioitu ja muokattu täältä:
    http://webdesign.tutsplus.com/tutorials/complete-websites/twitter-bootstrap-101-introduction/


-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Blogi 0.1</title>
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
        <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

        <!-- Le styles -->
        <link href="${pageContext.request.contextPath}/resources/bootstrap.css" rel="stylesheet">
        <style type="text/css">
            /* Override some defaults */
            html, body {
                background-color: #eee;
            }
            body {
                padding-top: 40px; /* 40px to make the container go all the way to the bottom of the topbar */
            }
            .container > footer p {
                text-align: center; /* center align it with the container */
            }
            .container {
                width: 820px; /* downsize our container to make the content feel a bit tighter and more cohesive. NOTE: this removes two full columns from the grid, meaning you only go to 14 columns and not 16. */
            }

            /* The white background content wrapper */
            .container > .content {
                background-color: #fff;
                padding: 20px;
                margin: 0 -20px; /* negative indent the amount of the padding to maintain the grid system */
                -webkit-border-radius: 0 0 6px 6px;
                -moz-border-radius: 0 0 6px 6px;
                border-radius: 0 0 6px 6px;
                -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.15);
                -moz-box-shadow: 0 1px 2px rgba(0,0,0,.15);
                box-shadow: 0 1px 2px rgba(0,0,0,.15);
            }

            /* Page header tweaks */
            .page-header {
                background-color: #f5f5f5;
                padding: 20px 20px 10px;
                margin: -20px -20px 20px;
            }

            /* Styles you shouldn't keep as they are for displaying this base example only */
            /* Give a quick and non-cross-browser friendly divider */
            .content .span10, .span4 {
                word-wrap: break-word;
            }
            .content .span4 {
                padding-left: 19px;
                margin-left: 0;
                border-left: 1px solid #eee;
            }

            .topbar .btn {
                border: 0;
            }

            h1 {
                text-transform: capitalize;
            }

            textarea {
                width: 400px;
                height: 200px;
            }

            .comment {
                width: 300px;
                height: 100px;
            }

            span.error {
                color: red;
            }

        </style>

    </head>

    <body>

        <div class="topbar">
            <div class="fill">
                <div class="container">
                    <a class="brand" href="${pageContext.request.contextPath}/">Blogi</a>
                    <ul class="nav">
                        <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                        <!-- sec-Tageilla voidaan määritellä, että mitä elementtejä näkyy vain millekkin käyttäjäryhmälle -->
                        <sec:authorize access="hasRole('ROLE_USER')">
                            <li><a href="${pageContext.request.contextPath}/postform">Post</a></li>
                            <li><a href="${pageContext.request.contextPath}/j_spring_security_logout">Logout</a></li>
                        </sec:authorize>
                    </ul>



                    <sec:authorize access="isAnonymous()">
                        <form action="${pageContext.request.contextPath}/j_spring_security_check" class="pull-right" method="post">
                            <input class="input-small" type="text" placeholder="Username" name="j_username">
                            <input class="input-small" type="password" placeholder="Password" name="j_password">
                            <button class="btn" type="submit">Sign in</button>
                        </form>
                    </sec:authorize>
                </div>
            </div>
        </div>

        <div class="container">

            <div class="content">
                <div class="page-header">
                    <h1>${page} <small>blogi 0.1</small></h1>
                </div>
                <div class="row">
                    <div class="span10">
                        <h2></h2>
                        <jsp:include page="${page}.jsp" />
                    </div>
                    <div class="span4">
                        <h3>
                            <c:forEach items="${posts}" var="element"> 
                                <a href="${pageContext.request.contextPath}/post/${element.id}">${element.title}</a>
                                </br>
                            </c:forEach>
                        </h3>
                    </div>
                </div>
            </div>

            <footer>
                <p> </p>
            </footer>

        </div> <!-- /container -->

    </body>
</html>
