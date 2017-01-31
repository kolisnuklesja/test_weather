<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Spring Security Example </title>
</head>
<body>
<form:form action="/j_spring_security_check" method="post">
    <table class="login">

        <tr>
            <td colspan="2"><h1>Log-in</h1><br></td>
        </tr>
        <tr>
            <td>Login</td>
            <td><input type="text" name="j_username" placeholder="Username"></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="j_password" placeholder="Password"></td>
        </tr>

        <tr>
            <td colspan="2"><input type="checkbox" id="rememberme" name="remember"/>Запомнить меня</td>
        </tr>
        <tr>
            <td colspan="2" class="error"><c:if test="${param.error==1}">Неверно введены данные</c:if></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" name="login" class="login-submit" value="Login"></td>
        </tr>
    </table>
</form:form>

</body>
</html>