package com.weather.service.impl;

import com.weather.model.City;
import com.weather.repository.CityRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

/**
 * Created by Tanya on 07.02.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(secure = false)
public class CityServiceImplTest {

    @InjectMocks
    CityServiceImpl cityService;

    @Mock
    CityRepository cityRepository;

    @Mock
    City city;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void save() throws Exception {

    }

    @Test
    public void findByCityNameIfExist() throws Exception {
        when(cityRepository.findByName(anyString())).thenReturn(city);
        assertEquals(city, cityService.findOrCreateByCityName("test"));

    }

    @Test
    public void findByCityNameIfNotExist() throws Exception {
        when(cityRepository.findByName(anyString())).thenReturn(null);
        doReturn(city).when(cityRepository).save(city);
        assertEquals(city.getName(), eq(cityService.findOrCreateByCityName("test").getName()));
    }

}