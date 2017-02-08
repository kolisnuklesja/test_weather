package com.weather.service;

import com.weather.dto.WeatherDTO;
import com.weather.entity.Time;
import com.weather.model.City;
import com.weather.model.User;
import com.weather.model.Weather;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Tanya on 02.02.2017.
 */
public interface WeatherService {
    void save(List<Time> weatherList, City city);

    void saveUserForecast(WeatherDTO weatherDTO, User user, City city);

    List<String> getDistinctCloudsName();

    List<String> getDistinctWindSpeed();

    List<String> getDistinctWindDirection();

    double getAVGTempretureFromTo(City city, java.util.Date from, java.util.Date to);
}
