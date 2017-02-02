package com.weather.service.impl;

import com.weather.model.City;
import com.weather.repository.CityRepository;
import com.weather.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Tanya on 02.02.2017.
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public void save(City city) {
        cityRepository.save(city);
    }

    @Override
    public City findByCityName(String name) {
        City city = cityRepository.findByName(name);
        if (city == null)
        {
            city = new City();
            city.setName(name);
            cityRepository.save(city);
        }

        return city;
    }
}
