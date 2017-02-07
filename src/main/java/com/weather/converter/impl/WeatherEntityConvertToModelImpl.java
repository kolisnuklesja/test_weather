package com.weather.converter.impl;

import com.weather.converter.WeatherEntityConvertToModel;
import com.weather.dto.WeatherDTO;
import com.weather.entity.Time;
import com.weather.model.Weather;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Tanya on 02.02.2017.
 */
@Service
public class WeatherEntityConvertToModelImpl implements WeatherEntityConvertToModel {

    private Weather weather;
    private SimpleDateFormat format;

    public WeatherEntityConvertToModelImpl() {
        format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        weather = new Weather();
    }

    @Override
    public Weather converter(Time time) {

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

    @Override
    public Weather converter(WeatherDTO weatherDTO) {
      format = new SimpleDateFormat("yyyy-MM-dd");


        try {
            weather.setTimeFrom(format.parse(weatherDTO.getTime()));
            weather.setTimeTo(format.parse(weatherDTO.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        weather.setCloudsName(weatherDTO.getCloudsName());
        weather.setTemperatureValue(weatherDTO.getTempretureValue().doubleValue());
        weather.setWindSpeedName(weatherDTO.getWindSpeedName());
        weather.setWindDirection(weatherDTO.getWindDirection());
        weather.setHumidityValue(weatherDTO.getHumidity().intValue());
        return weather;
    }
}
