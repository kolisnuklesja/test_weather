<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
    <script src="http://wisdomweb.ru/editor/localization.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#datepicker").datepicker({dateFormat:"yy-mm-dd"});

        });
    </script>
</head>
<body>
<form:form action="/forecast/new" method="post" modelAttribute="forecastForm">
    <table>
        <tr>
            <td colspan="2"><h1>Add Forecast</h1><br></td>
        </tr>
        <tr>
            <td>Date</td>
            <td><form:input type="text" path="time" autofocus="true" id="datepicker"/></td>
            <td><font color="red"> <form:errors path="time"></form:errors></font></td>
        </tr>
        <tr>
            <td>Temperature</td>
            <td><form:input type="text" path="tempretureValue"/></td>
            <td><font color="red"> <form:errors path="tempretureValue"></form:errors></font></td>
        </tr>
        <tr>
            <td>Clouds</td>
            <td>
                <form:select path="cloudsName">
                    <form:option value=""></form:option>
                    <c:forEach items="${clouds}" var="cloud">
                        <form:option value="${cloud}">${cloud}</form:option>
                    </c:forEach>
                </form:select>
            </td>
        </tr>
        <tr>
            <td>Wind sepeed</td>
            <td>
                <form:select path="windSpeedName">
                    <form:option value=""></form:option>
                    <c:forEach items="${windSpeeds}" var="windSpeed">
                        <form:option value="${windSpeed}">${windSpeed}</form:option>
                    </c:forEach>
                </form:select>
            </td>
        </tr>
        <tr>
            <td>Wind direction</td>
            <td>
                <form:select path="windDirection">
                    <form:option value=""></form:option>
                    <c:forEach items="${windDirections}" var="windDirection">
                        <form:option value="${windDirection}">${windDirection}</form:option>
                    </c:forEach>
                </form:select>
            </td>
        </tr>
        <tr>
            <td>Humidity</td>
            <td><form:input type="text" path="humidity"/></td>
            <td><font color="red"> <form:errors path="humidity"></form:errors></font></td>
        </tr>
        <td colspan="2"><input type="submit" value="Add"></td>
        </tr>
        <tr>
            <td colspan="2"><a href="/login">Главная</a></td>
        </tr>
    </table>
</form:form>
</body>
</html>
