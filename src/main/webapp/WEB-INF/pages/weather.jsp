<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Weather</title>
</head>
<body>
<form:form modelAttribute="weather">
    <table>
        <tr>
            <td>Country</td>
            <td>
                <c:out value="${weather.country}"/>
            </td>
        </tr>
        <tr>
            <c:forEach var="forecast" items="${weather.forecastList}">
                <td>
                    <table>
                        <tr>
                            <td>Date</td>
                            <td><c:out value="${forecast.date}"/></td>
                        </tr>
                        <tr>
                            <td>From</td>
                            <td><c:out value="${forecast.from}"/></td>
                        </tr>
                        <tr>
                            <td>To</td>
                            <td><c:out value="${forecast.to}"/></td>
                        </tr>
                        <tr>
                            <td>Temperature Middle</td>
                            <td><c:out value="${forecast.temperatureValue} ${forecast.unit}"/></td>
                        </tr>
                        <tr>
                            <td>Temperature Min</td>
                            <td><c:out value="${forecast.temperatureMin} ${forecast.unit}"/></td>
                        </tr>
                        <tr>
                            <td>Temperature Max</td>
                            <td><c:out value="${forecast.temperatureMax} ${forecast.unit}"/></td>
                        </tr>

                        <tr>
                            <td>Wind Speed</td>
                            <td><c:out value="${forecast.windSpeed}"/></td>
                        </tr>
                        <tr>
                            <td>Wind Direction</td>
                            <td><c:out value="${forecast.windDirection}"/></td>
                        </tr>
                        <tr>
                            <td>humidity</td>
                            <td><c:out value="${forecast.humidityValue} ${forecast.humidityUnit}"/></td>
                        </tr>
                        <tr>
                            <td>Clouds</td>
                            <td><c:out value="${forecast.clouds}"/></td>
                        </tr>
                    </table>
                </td>
            </c:forEach>
        </tr>
    </table>
</form:form>
</body>
</html>
