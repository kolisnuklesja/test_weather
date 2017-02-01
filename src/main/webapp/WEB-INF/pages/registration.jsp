<%--
  Created by IntelliJ IDEA.
  User: Tanya
  Date: 01.02.2017
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form:form action="/register/new" method="post" modelAttribute="userForm">
    <%--<form:errors path="*" cssClass="errorblock" element="div"/>--%>
    <table>
        <tr>
            <td colspan="2"><h1>Registration</h1><br></td>
        </tr>

        <tr>
            <td>Login</td>
            <td><form:input type="text" path="username" placeholder="Username" autofocus="true"/></td>
            <td><form:errors path="username"/>
        </tr>

        <tr>
            <td>Password</td>
            <td>

                <form:input type="password" path="password" class="form-control" placeholder="Username"></form:input>
            </td>
            <td><form:errors path="password"/>

            </td>

        <tr>
            <td colspan="2"><input type="submit" value="Register"></td>
        </tr>
        <tr>
            <td colspan="2"><a href="/register">Авторизация</a></td>
        </tr>
    </table>
</form:form>
</body>
</html>
