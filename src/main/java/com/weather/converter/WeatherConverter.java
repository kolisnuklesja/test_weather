package com.weather.converter;

import com.weather.entity.Time;
import com.weather.model.Weather;

/**
 * Created by Tanya on 02.02.2017.
 */
public interface WeatherConverter {

    public Weather converter(Time time);
}
