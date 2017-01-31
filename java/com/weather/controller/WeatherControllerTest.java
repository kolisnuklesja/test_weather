package com.weather.controller;

import com.weather.entity.WeatherData;
import com.weather.xml.WeatherXMLParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Created by Tanya on 27.01.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherControllerTest {

    @MockBean
    private WeatherXMLParser weatherXMLParser;

    @MockBean
    private WeatherData weatherData;

    //    @Before
//    public void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(new WeatherController()).build();
//    }
//    @Test
////    @WithMockUser(username="admin",roles="USER")
//    public void testIndex() throws Exception{
//        this.mockMvc.perform(get("/login/weather"))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
    @Test
    public void getWeather() throws Exception {
//        given(this.remoteService.someCall()).willReturn("mock");
//        String reverse = reverser.reverseSomeCall();
//        assertThat(reverse).isEqualTo("kcom");
    }

}