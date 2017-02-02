package com.weather.converter.impl;

import com.weather.converter.WeatherConverter;
import com.weather.entity.Time;
import com.weather.model.Weather;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tanya on 02.02.2017.
 */
@Service
public class WeatherConverterImpl implements WeatherConverter {

    @Override
    public Weather converter(Time time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Weather weather = new Weather();
        try {
            weather.setTimeFrom(format.parse(time.getFrom()));
            weather.setTimeTo(format.parse(time.getTo()));
            weather.setCloudsName(time.getClouds().getValue());
            weather.setCloudsValue(Integer.valueOf(time.getClouds().getAll()));
            weather.setHumidityValue(Integer.valueOf(time.getHumidity().getValue()));
            weather.setTemperatureMax(Double.valueOf(time.getTemperature().getMax()));
            weather.setTemperatureMin(Double.valueOf(time.getTemperature().getMin()));
            weather.setTemperatureValue(Double.valueOf(time.getTemperature().getValue()));
            weather.setWindSpeedName(time.getWindSpeed().getName());
            weather.setWindSpeedValue(Double.valueOf(time.getWindSpeed().getMps()));
            weather.setWindDirection(time.getWindDirection().getName());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return weather;
    }
}
