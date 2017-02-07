package com.weather.service.impl;

import com.weather.converter.WeatherEntityConvertToModel;
import com.weather.dto.WeatherDTO;
import com.weather.entity.Time;
import com.weather.model.City;
import com.weather.model.User;
import com.weather.model.Weather;
import com.weather.repository.WeatherRepository;
import com.weather.service.CityService;
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
    private WeatherEntityConvertToModel weatherEntityConvertToModel;

    @Autowired
    private CityService cityService;

    @Override
    public void save(List<Time> weatherList, City city) {
        Weather weather, weatherSearch;
        for (Time time : weatherList) {
            weather = weatherEntityConvertToModel.converter(time);
            weather.setCity(city);
            weatherSearch = weatherRepository.findByCityAndTimeFrom(city, weather.getTimeFrom());
            if (weatherSearch != null) {
                weather.setIdweather(weatherSearch.getIdweather());
            }
            weatherRepository.save(weather);

        }

    }

    @Override
    public void saveUserForecast(WeatherDTO weatherDTO, User user) {
        Weather weather = weatherEntityConvertToModel.converter(weatherDTO);
        weather.setUser(user);
        weather.setCity(cityService.findByCityName("Minsk"));
        weatherRepository.save(weather);

    }

    @Override
    public List<String> getDistinctCloudsName() {
        return weatherRepository.findDistinctCloudsName();
    }

    @Override
    public List<String> getDistinctWindSpeed() {
        return weatherRepository.findDistinctWindSpeed();
    }

    @Override
    public List<String> getDistinctWindDirection() {
        return weatherRepository.findDistinctWindDirection();
    }
}
