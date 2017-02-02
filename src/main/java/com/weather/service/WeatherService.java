package com.weather.service;

import com.weather.entity.Time;
import com.weather.model.City;
import com.weather.model.Weather;

import java.util.List;

/**
 * Created by Tanya on 02.02.2017.
 */
public interface WeatherService {
    void save(List<Time> weatherList, City city);
}
