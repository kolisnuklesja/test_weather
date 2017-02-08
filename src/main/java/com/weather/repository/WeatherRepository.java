package com.weather.repository;

import com.weather.model.City;
import com.weather.model.Weather;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by Tanya on 02.02.2017.
 */
public interface WeatherRepository extends CrudRepository<Weather, Long> {

    Weather findByCityAndTimeFrom(City city, java.util.Date timeFrom);

    @Query("select distinct w.cloudsName from Weather w where w.user = null")
    List<String> findDistinctCloudsName();

    @Query("select distinct w.windSpeedName from Weather w where w.user = null")
    List<String> findDistinctWindSpeed();

    @Query("select distinct w.windDirection from Weather w where w.user = null")
    List<String> findDistinctWindDirection();

    @Query("select AVG(w.temperatureValue) from  Weather w where w.user = null  and w.city.idcity = ?1 and " +
            "w.timeFrom between ?2 and ?3 and w.timeTo between ?2 and ?3")
    double findAVGTemperetureFromTo(Long idcity, Date from, Date to);

}
//    and w.city = ?1 " +
//        "and w.timeFrom between ?2 and ?3 and w.timeTo between ?2 and ?3

//    City city, Date from, Date to