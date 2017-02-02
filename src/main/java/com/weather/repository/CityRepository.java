package com.weather.repository;

import com.weather.model.City;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Tanya on 02.02.2017.
 */
public interface CityRepository extends CrudRepository<City, Long> {
    City findByName(String name);
}
