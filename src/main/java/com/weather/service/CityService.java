package com.weather.service;

import com.weather.model.City;

/**
 * Created by Tanya on 02.02.2017.
 */
public interface CityService {

    void save(City city);

    City findByCityName(String name);

}
