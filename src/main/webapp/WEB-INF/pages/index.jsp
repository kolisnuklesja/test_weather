<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <script src="<c:url value="/resources/core/jquery.1.10.2.min.js" />"></script>
    <script src="<c:url value="/resources/core/jquery.autocomplete.min.js" />"></script>
    <script src="<c:url value="/resources/js/weatherRequest.js" />"></script>
</head>
<body>


<form:form  action="/j_spring_security_logout" method="post">
    <input type="submit" value="Exit">
    <p>Enter your city</p>
    <input type="text" name="city" id="searchText">
    <input type="button" value="Get weather" id="but">
</form:form>
<div id="results"></div>
</body>
</html>
