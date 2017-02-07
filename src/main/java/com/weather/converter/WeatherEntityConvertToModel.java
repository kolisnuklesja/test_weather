package com.weather.converter;

import com.weather.dto.WeatherDTO;
import com.weather.entity.Time;
import com.weather.model.Weather;

/**
 * Created by Tanya on 02.02.2017.
 */
public interface WeatherEntityConvertToModel {

    Weather converter(Time time);

    Weather converter(WeatherDTO weatherDTO);
}
