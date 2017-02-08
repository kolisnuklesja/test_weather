package com.weather.controller;

import com.weather.dto.WeatherDTO;
import com.weather.entity.Forecast;
import com.weather.entity.Time;
import com.weather.entity.WeatherData;
import com.weather.model.City;
import com.weather.model.User;
import com.weather.service.CityService;
import com.weather.service.UserService;
import com.weather.service.WeatherService;
import com.weather.validator.UserValidator;
import com.weather.xml.WeatherXMLParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Tanya on 27.01.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(secure = false)
public class WeatherControllerTest {

    @Autowired
    private MockMvc mvc;

    @InjectMocks
    WeatherController weatherController;

    @Mock
    UserService userService;

    @Mock
    WeatherXMLParser weatherXMLParser;

    @Mock
    CityService cityService;

    @Mock
    User user;

    @Mock
    BindingResult bindingResult;

    @Mock
    UserValidator userValidator;

    @Mock
    WeatherService weatherService;

    @MockBean
    WeatherService wservice;

    @Mock
    Authentication authentication;

    @Mock
    WeatherDTO weatherDTO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testWelcome() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andDo(print());
    }

    @Test
    public void testLogin() throws Exception {
        mvc.perform(get("/login")
                .with(user("test").password("test").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andDo(print());
    }

    @Test
    public void testGetRegisterPage() throws Exception {
        mvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"))
                .andExpect(model().attributeExists("userForm"))
                .andDo(print());
    }

    @Test
    public void testRegisterUserWhenDataIncorrect() throws Exception {
        doNothing().when(userValidator).validate(anyObject(), anyObject());
        Mockito.doNothing().when(userService).save(anyObject());
        doReturn(false).when(bindingResult).hasErrors();
        assertEquals("redirect:/", weatherController.registerUser(user, bindingResult));
    }

    @Test
    public void testRegisterUserWhenDataCorrect() throws Exception {
        doNothing().when(userValidator).validate(anyObject(), anyObject());
        Mockito.doNothing().when(userService).save(anyObject());
        doReturn(true).when(bindingResult).hasErrors();
        assertEquals("registration", weatherController.registerUser(user, bindingResult));
    }

    @Test
    public void testGetWeatherWhenCorrectData() throws Exception {
        Time time = new Time();
        time.setFrom("1");
        time.setTo("1");
        Forecast forecast = new Forecast();
        forecast.setTimeList(Arrays.asList(time));
        WeatherData weatherData = new WeatherData();
        weatherData.setForecast(forecast);
        when(weatherXMLParser.getTimeListByCity(anyString())).thenReturn(weatherData);
        doNothing().when(weatherService).save(anyObject(), anyObject());
        doReturn(new City()).when(cityService).findOrCreateByCityName(anyString());
        assertEquals(Arrays.asList(time), weatherController.getWeather(anyString()));

    }

    @Test
    public void testGetWeatherWhenIncorrectData() throws Exception {

        when(weatherXMLParser.getTimeListByCity(anyString())).thenReturn(null);
        doNothing().when(weatherService).save(anyObject(), anyObject());
        doReturn(new City()).when(cityService).findOrCreateByCityName(anyString());
        assertEquals(null, weatherController.getWeather(anyString()));

    }

    @Test
    public void testError() throws Exception {
        mvc.perform(get("/error403"))
                .andExpect(status().isOk())
                .andExpect(view().name("error403"))
                .andDo(print());
    }

    @Test
    public void testGetForecastPage() throws Exception {
        when(wservice.getDistinctCloudsName()).thenReturn(Arrays.asList("1", "2", "3"));
        when(wservice.getDistinctWindSpeed()).thenReturn(Arrays.asList("4", "5"));
        when(wservice.getDistinctWindDirection()).thenReturn(Arrays.asList("6"));
        mvc.perform(get("/forecast"))
                .andExpect(status().isOk())
                .andExpect(view().name("userforecast"))
                .andExpect(model().attributeExists("forecastForm"))
                .andExpect(model().attribute("clouds", is(Arrays.asList("1", "2", "3"))))
                .andExpect(model().attribute("windSpeeds", is(Arrays.asList("4", "5"))))
                .andExpect(model().attribute("windDirections", is(Arrays.asList("6"))))
                .andDo(print());
    }

    @Test
    public void testAddForecastByUserWhenCorrectData() throws Exception {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(authentication.getName()).thenReturn("test");
        when(userService.findByUsername(anyString())).thenReturn(user);
        doNothing().when(weatherService).saveUserForecast(weatherDTO, user,anyObject());
        assertEquals("redirect:/login", weatherController.addForecastByUser(weatherDTO, bindingResult, authentication));
    }

    @Test
    public void testAddForecastByUserWhenIncorrectData() throws Exception {
        when(bindingResult.hasErrors()).thenReturn(true);
        when(authentication.getName()).thenReturn("test");
        when(userService.findByUsername(anyString())).thenReturn(user);
        doNothing().when(weatherService).saveUserForecast(weatherDTO, user, anyObject());
        assertEquals("userforecast", weatherController.addForecastByUser(weatherDTO, bindingResult, authentication));

    }
}