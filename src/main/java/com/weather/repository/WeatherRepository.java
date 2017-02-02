package com.weather.repository;

import com.weather.model.City;
import com.weather.model.Weather;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * Created by Tanya on 02.02.2017.
 */
public interface WeatherRepository extends CrudRepository<Weather, Long> {

    Weather findByCityAndTimeFrom(City city, Date timeFrom);

}
