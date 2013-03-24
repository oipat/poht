<%-- 
    Document   : autot
    Created on : Mar 10, 2013, 4:56:44 PM
    Author     : tapio
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <form:form method="post" action="/autot" commandName="auto" name="autoForm" id="autoForm">
        <table>
            <tr>
                <td><form:label path="rekno">Rekisteritunnus</form:label></td>
                <td><form:input path="rekno" /></td>
            </tr>
            <tr>
                <td><form:label path="merkki">Merkki</form:label></td>
                <td><form:input path="merkki" /></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Lisää"/>
                </td>
            </tr>
        </table> 
    </form:form>
</body>
</html>
