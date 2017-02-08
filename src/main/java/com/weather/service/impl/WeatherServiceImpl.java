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

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Override
    public void save(List<Time> weatherList, City city) {
        Weather weather, weatherSearch;
        for (Time time:weatherList) {
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
    public void saveUserForecast(WeatherDTO weatherDTO, User user, City city) {
        Weather weather = weatherEntityConvertToModel.converter(weatherDTO);
        weather.setUser(user);
        weather.setCity(city);
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

    @Override
    public double getAVGTempretureFromTo(City city, java.util.Date from, java.util.Date to)
    {
        return weatherRepository.findAVGTemperetureFromTo(city.getIdcity(),from,to);

    }
}
