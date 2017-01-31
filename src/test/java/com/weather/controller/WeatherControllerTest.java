package com.weather.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.entity.*;
import com.weather.xml.WeatherXMLParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Tanya on 27.01.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class WeatherControllerTest {


//    @Autowired
//    private WeatherXMLParser weatherXMLParser;

    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new WeatherController()).build();
    }

    @Test
    public void testIndex() throws Exception {
        mvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andDo(print());
    }

    @Test
    public void testIndex2() throws Exception {
        mvc.perform(get("/login/weather")
                .param("city", "Minsk"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testGetWeather() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        WeatherXMLParser mock = org.mockito.Mockito.mock(WeatherXMLParser.class);
        when(mock.getTimeListByCity("city"))
                .thenReturn(setWeatherDataEntity());
        mvc.perform(post("/login/weather")
                .param("city", "city"))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper
                        .writeValueAsString(setWeatherDataEntity().getForecast().getTimeList())))
                .andDo(print());
    }

    public WeatherData setWeatherDataEntity() {
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

}