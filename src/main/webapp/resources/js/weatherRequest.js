/**
 * Created by Tanya on 25.01.2017.
 */

$(document).ready(function () {
    $('#but').click(function () {
        var searchText = $('#searchText').val();
        if (searchText.length > 0) {
            ajaxRequest(searchText);
        }
        else {
            alert("Enter city");
        }
    });
});

function ajaxRequest(term) {
    $.ajax({
        type: 'GET',
        url: '/login/weather',
        data: {'city': term},
        success: function (data) {
            $('#results').html('');
            if (data != null) {
                $.each(data, function (i, forecast) {
                    var $filmTable = $('<table><tr></tr></table>');
                    $.each(forecast, function (keyAttr, valAttr) {
                        if (keyAttr === "from") {
                            var $filtAttrTr = $('<td>From</td><td>' + valAttr + '</td>');
                            $filmTable.append($filtAttrTr);
                        }
                        else if (keyAttr === "to") {
                            var $filtAttrTr = $('<td>To</td><td>' + valAttr + '</td>');
                            $filmTable.append($filtAttrTr);
                        }
                        else if (keyAttr === "temperature") {
                            var $filtAttrTr = $('<td>Temperature Value</td><td>' + valAttr.value + '</td>');
                            $filmTable.append($filtAttrTr);
                            var $filtAttrTr = $('<td>Temperature Min Value</td><td>' + valAttr.min + '</td>');
                            $filmTable.append($filtAttrTr);
                            var $filtAttrTr = $('<td>Temperature Max Value</td><td>' + valAttr.max + '</td>');
                            $filmTable.append($filtAttrTr);
                        }
                        else if (keyAttr === "windSpeed") {
                            var $filtAttrTr = $('<td>Wind Speed</td><td>' + valAttr.name + '</td>');
                            $filmTable.append($filtAttrTr);
                        }
                        else if (keyAttr === "windDirection") {
                            var $filtAttrTr = $('<td>Wind Direction</td><td>' + valAttr.name + '</td>');
                            $filmTable.append($filtAttrTr);
                        }
                        else if (keyAttr === "humidity") {
                            var $filtAttrTr = $('<td>Humidity</td><td>' + valAttr.value + '</td>');
                            $filmTable.append($filtAttrTr);
                        }
                        else if (keyAttr === "clouds") {
                            var $filtAttrTr = $('<td>Clouds</td><td>' + valAttr.value + '</td>');
                            $filmTable.append($filtAttrTr);
                        }
                    });
                    $('#results').append($filmTable);
                });
            }
            else {
                var $ex = $('Error');
                $('#results').append($ex);
            }
        }
    });
}