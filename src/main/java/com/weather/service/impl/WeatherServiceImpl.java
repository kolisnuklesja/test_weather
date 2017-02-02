package com.weather.service.impl;

import com.weather.converter.WeatherConverter;
import com.weather.entity.Time;
import com.weather.model.City;
import com.weather.model.Weather;
import com.weather.repository.WeatherRepository;
import com.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Tanya on 02.02.2017.
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private WeatherConverter weatherConverter;

    @Override
    public void save(List<Time> weatherList, City city) {
        Weather weather, weatherSearch;
        for (Time time : weatherList) {
            weather = weatherConverter.converter(time);
            weather.setCity(city);
            weatherSearch = weatherRepository.findByCityAndTimeFrom(city, weather.getTimeFrom());
            if (weatherSearch == null) {
                weatherRepository.save(weather);
            } else {
                weather.setIdweather(weatherSearch.getIdweather());
                weatherRepository.save(weather);
            }

        }

    }
}
