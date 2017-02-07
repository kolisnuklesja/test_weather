package com.weather.controller;

import com.weather.dto.WeatherDTO;
import com.weather.model.User;
import com.weather.service.UserService;
import com.weather.service.WeatherService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Tanya on 06.02.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(secure = false)
public class WeatherControllerValidationTest {

    @Autowired
    private MockMvc mvc;

    @InjectMocks
    WeatherController weatherController;

    @MockBean
    UserService userService;

    @MockBean
    WeatherService weatherService;




    @Mock
    WeatherDTO weatherDTO;

    @Mock
    BindingResult bindingResult;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterUserWhenNotValidUsername() throws Exception {
        mvc.perform(get("/register/new")
                .accept(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "user")
                .param("password", "passpass")
                .sessionAttr("userFrom", new User()))
                .andExpect(view().name("registration"))
                .andExpect(model().attributeHasFieldErrorCode("userForm", "username",
                        "Size"))
                .andDo(print());

    }

    @Test
    public void testRegisterUserWhenNotValidPassword() throws Exception {
        mvc.perform(get("/register/new")
                .accept(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "userus")
                .param("password", "pass")
                .sessionAttr("userFrom", new User()))
                .andExpect(view().name("registration"))
                .andExpect(model().attributeHasFieldErrorCode("userForm", "password",
                        "Size"))
                .andDo(print());

    }

    @Test
    public void testRegisterUserWhenUsernameIsDuplicated() throws Exception {
        doReturn(new User()).when(userService).findByUsername(anyString());
        mvc.perform(get("/register/new")
                .accept(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "user12")
                .param("password", "passpass")
                .sessionAttr("userFrom", new User()))
                .andExpect(view().name("registration"))
                .andExpect(model().attributeHasFieldErrorCode("userForm", "username",
                        "Duplicate"))
                .andDo(print());

    }

    @Test
    public void testRegisterUserWhenAllDataCorrect() throws Exception {

        doNothing().when(userService).save(anyObject());
        mvc.perform(get("/register/new")
                .accept(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "useruser")
                .param("password", "passpass")
                .sessionAttr("userFrom", new User()))
                .andExpect(redirectedUrl("/"))
                .andDo(print());

    }

    @Test
    public void testAddForecastByUserWhenTimeIncorrect() throws Exception {
        mvc.perform(get("/forecast/new")
                .accept(MediaType.APPLICATION_FORM_URLENCODED)
                .param("time", "")
                .param("tempretureValue", "0")
                .param("humidity", "0")
                .sessionAttr("forecastForm", new WeatherDTO()))
                .andExpect(view().name("userforecast"))
                .andExpect(model().attributeHasFieldErrorCode("forecastForm", "time",
                        "NotEmpty"))
                .andDo(print());


    }

    @Test
    public void testAddForecastByUserWhenTempretureValueIncorrect() throws Exception {
        mvc.perform(get("/forecast/new")
                .accept(MediaType.APPLICATION_FORM_URLENCODED)
                .param("time", "2016-15-10")
                .param("tempretureValue", "")
                .param("humidity", "0")
                .sessionAttr("forecastForm", new WeatherDTO()))
                .andExpect(view().name("userforecast"))
                .andExpect(model().attributeHasFieldErrorCode("forecastForm", "tempretureValue",
                        "NotNull"))
                .andDo(print());

        mvc.perform(get("/forecast/new")
                .accept(MediaType.APPLICATION_FORM_URLENCODED)
                .param("time", "2016-15-10")
                .param("tempretureValue", "51")
                .param("humidity", "0")
                .sessionAttr("forecastForm", new WeatherDTO()))
                .andExpect(view().name("userforecast"))
                .andExpect(model().attributeHasFieldErrorCode("forecastForm", "tempretureValue",
                        "DecimalMax"))
                .andDo(print());

        mvc.perform(get("/forecast/new")
                .accept(MediaType.APPLICATION_FORM_URLENCODED)
                .param("time", "2016-15-10")
                .param("tempretureValue", "-51")
                .param("humidity", "0")
                .sessionAttr("forecastForm", new WeatherDTO()))
                .andExpect(view().name("userforecast"))
                .andExpect(model().attributeHasFieldErrorCode("forecastForm", "tempretureValue",
                        "DecimalMin"))
                .andDo(print());

    }

    @Test
    public void testAddForecastByUserWhenHumidityIncorrect() throws Exception {
        mvc.perform(get("/forecast/new")
                .accept(MediaType.APPLICATION_FORM_URLENCODED)
                .param("time", "")
                .param("tempretureValue", "0")
                .param("humidity", "")
                .sessionAttr("forecastForm", new WeatherDTO()))
                .andExpect(view().name("userforecast"))
                .andExpect(model().attributeHasFieldErrorCode("forecastForm", "humidity",
                        "NotNull"))
                .andDo(print());
        mvc.perform(get("/forecast/new")
                .accept(MediaType.APPLICATION_FORM_URLENCODED)
                .param("time", "")
                .param("tempretureValue", "0")
                .param("humidity", "-1")
                .sessionAttr("forecastForm", new WeatherDTO()))
                .andExpect(view().name("userforecast"))
                .andExpect(model().attributeHasFieldErrorCode("forecastForm", "humidity",
                        "DecimalMin"))
                .andDo(print());
        mvc.perform(get("/forecast/new")
                .accept(MediaType.APPLICATION_FORM_URLENCODED)
                .param("time", "")
                .param("tempretureValue", "0")
                .param("humidity", "101")
                .sessionAttr("forecastForm", new WeatherDTO()))
                .andExpect(view().name("userforecast"))
                .andExpect(model().attributeHasFieldErrorCode("forecastForm", "humidity",
                        "DecimalMax"))
                .andDo(print());

    }


}
