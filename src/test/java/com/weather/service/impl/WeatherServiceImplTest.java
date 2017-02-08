package com.weather.service.impl;

import com.weather.converter.WeatherEntityConvertToModel;
import com.weather.model.City;
import com.weather.model.Weather;
import com.weather.repository.WeatherRepository;
import com.weather.service.CityService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

/**
 * Created by Tanya on 07.02.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(secure = false)
public class WeatherServiceImplTest {

    @InjectMocks
    WeatherServiceImpl weatherService;

    @MockBean
    private WeatherRepository weatherRepository;

    @MockBean
    private WeatherEntityConvertToModel weatherEntityConvertToModel;

    @MockBean
    private CityService cityService;

    @Mock
    private City city;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void save() throws Exception {
//        Weather weather, weatherSearch;
//        for (Time time : weatherList) {
//            weather = weatherEntityConvertToModel.converter(time);
//            weather.setCity(city);
//            weatherSearch = weatherRepository.findByCityAndTimeFrom(city, weather.getTimeFrom());
//            if (weatherSearch != null) {
//                weather.setIdweather(weatherSearch.getIdweather());
//            }
//            weatherRepository.save(weather);
//
//        }


    }

    @Test
    public void saveUserForecast() throws Exception {

    }

    @Test
    public void getDistinctCloudsName() throws Exception {

    }

    @Test
    public void getDistinctWindSpeed() throws Exception {

    }

    @Test
    public void getDistinctWindDirection() throws Exception {

    }

}