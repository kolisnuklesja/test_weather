<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <script src="<c:url value="/resources/core/jquery.1.10.2.min.js" />"></script>
    <script src="<c:url value="/resources/core/jquery.autocomplete.min.js" />"></script>
    <script src="<c:url value="/resources/js/weatherRequest.js" />"></script>
    <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
    <script src="http://wisdomweb.ru/editor/localization.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#datepicker").datepicker({format: "yy-mm-dd"});

        });
    </script>
</head>
<body>


<form:form  action="/j_spring_security_logout" method="post">
    <input type="submit" value="Exit">
    <p>Enter your city</p>
    <input type="text" name="city" id="searchText">
    <input type="button" value="Get weather" id="but">
</form:form>
<a href="/forecast">Добавить прогноз за день</a><br>
<a href="/avgtempreture">Получить среднее значение за год</a>
<%--<input type="text" name="timeFrom" autofocus="true" id="datepicker"/>--%>

<div>${result}</div>
<div id="results"></div>
</body>
</html>
