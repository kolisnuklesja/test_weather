package com.weather.xml;

import com.weather.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.ExpectedCount.manyTimes;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Created by Tanya on 27.01.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Commit
public class WeatherXMLParserTest {

    @Autowired
    private WeatherXMLParser weatherXMLParser;

    @Autowired
    private WeatherData weatherData;


    @Test
    public void testGetWeatherData() throws Exception {
        MockRestServiceServer server = MockRestServiceServer.bindTo(weatherXMLParser.getRestTemplate()).build();
        server.expect(manyTimes(), requestTo("http://api.openweathermap.org/")).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("<?xml version=\"1.0\"?><weatherdata>\n" +
                        "<location></location>\n" +
                        "<credit/>\n" +
                        "<meta></meta>\n" +
                        "<sun rise=\"2017-01-27T06:18:04\" set=\"2017-01-27T14:57:41\"/>\n" +
                        "<forecast>\n" +
                        "<time from=\"2017-01-27T06:00:00\" to=\"2017-01-27T09:00:00\">\n" +
                        "<symbol number=\"500\" name=\"light rain\" var=\"10d\"/>\n" +
                        "<precipitation unit=\"3h\" value=\"0.105\" type=\"rain\"/>\n" +
                        "<windDirection deg=\"311.003\" code=\"NW\" name=\"Northwest\"/>\n" +
                        "<windSpeed mps=\"4.45\" name=\"Gentle Breeze\"/>\n" +
                        "<temperature unit=\"celsius\" value=\"1.13\" min=\"1.02\" max=\"1.13\"/>\n" +
                        "<pressure unit=\"hPa\" value=\"1024.42\"/>\n" +
                        "<humidity value=\"99\" unit=\"%\"/>\n" +
                        "<clouds value=\"broken clouds\" all=\"76\" unit=\"%\"/>\n" +
                        "</time></forecast>" +
                        "</weatherdata>", MediaType.APPLICATION_XML));
        weatherData = weatherXMLParser.getWeatherData("http://api.openweathermap.org/");
        isEqualTo(getWeatherDataEntity(), weatherData);

    }


    @Test(expected = HttpMessageNotReadableException.class)
    public void testGetWeatherData2() throws Exception {
        MockRestServiceServer server = MockRestServiceServer.bindTo(weatherXMLParser.getRestTemplate()).build();
        server.expect(manyTimes(), requestTo("http://api.openweathermap.org/")).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("<?xml version=\"1.0\"?><weatherdata>\n" +
                        "<location></location>\n" +
                        "<credit/>\n" +
                        "<meta></meta>\n" +
                        "</time></forecast>" +
                        "</weatherdata>", MediaType.APPLICATION_XML));
        weatherData = weatherXMLParser.getWeatherData("http://api.openweathermap.org/");

    }

    private WeatherData getWeatherDataEntity() {
        WeatherData weatherData = new WeatherData();
        Forecast forecast = new Forecast();
        List<Time> timeList = new ArrayList<>();
        Time time = new Time();
        time.setTo("2017-01-27T09:00:00");
        time.setFrom("2017-01-27T06:00:00");
        Clouds clouds = new Clouds();
        clouds.setValue("broken clouds");
        clouds.setAll("76");
        clouds.setUnit("%");
        time.setClouds(clouds);
        Humidity humidity = new Humidity();
        humidity.setValue("99");
        humidity.setUnit("%");
        time.setHumidity(humidity);
        Temperature temperature = new Temperature();
        temperature.setUnit("celsius");
        temperature.setValue("1.13");
        temperature.setMax("1.13");
        temperature.setMin("1.02");
        time.setTemperature(temperature);
        WindDirection windDirection = new WindDirection();
        windDirection.setName("Northwest");
        time.setWindDirection(windDirection);
        WindSpeed windSpeed = new WindSpeed();
        windSpeed.setName("Gentle Breeze");
        windSpeed.setMps("4.45");
        time.setWindSpeed(windSpeed);
        timeList.add(time);
        forecast.setTimeList(timeList);
        weatherData.setForecast(forecast);
        return weatherData;
    }

    private void isEqualTo(WeatherData current, WeatherData expected) {
        List<Time> currentTimeList = current.getForecast().getTimeList();
        List<Time> expectedTimeList = expected.getForecast().getTimeList();
        for (int i = 0; i < currentTimeList.size(); i++) {
            Time currentTime = currentTimeList.get(i),
                    expectedTime = expectedTimeList.get(i);
            assertThat(expectedTime.getFrom()).isEqualTo(currentTime.getFrom());
            assertThat(expectedTime.getTo()).isEqualTo(currentTime.getTo());
            assertThat(expectedTime.getClouds().getAll()).isEqualTo(currentTime.getClouds().getAll());
            assertThat(expectedTime.getClouds().getUnit()).isEqualTo(currentTime.getClouds().getUnit());
            assertThat(expectedTime.getClouds().getValue()).isEqualTo(currentTime.getClouds().getValue());
            assertThat(expectedTime.getHumidity().getValue()).isEqualTo(currentTime.getHumidity().getValue());
            assertThat(expectedTime.getHumidity().getUnit()).isEqualTo(currentTime.getHumidity().getUnit());
            assertThat(expectedTime.getTemperature().getUnit()).isEqualTo(currentTime.getTemperature().getUnit());
            assertThat(expectedTime.getTemperature().getValue()).isEqualTo(currentTime.getTemperature().getValue());
            assertThat(expectedTime.getTemperature().getMax()).isEqualTo(currentTime.getTemperature().getMax());
            assertThat(expectedTime.getTemperature().getMin()).isEqualTo(currentTime.getTemperature().getMin());
            assertThat(expectedTime.getWindDirection().getName()).isEqualTo(currentTime.getWindDirection().getName());
            assertThat(expectedTime.getWindSpeed().getName()).isEqualTo(currentTime.getWindSpeed().getName());
            assertThat(expectedTime.getWindSpeed().getMps()).isEqualTo(currentTime.getWindSpeed().getMps());
        }
    }

}