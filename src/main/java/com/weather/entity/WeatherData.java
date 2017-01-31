package com.weather.entity;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Tanya on 27.01.2017.
 */
@Component
@XmlRootElement(name = "weatherdata")
@XmlAccessorType(XmlAccessType.NONE)
public class WeatherData {

    @XmlElement(name = "forecast")
    private Forecast forecast;

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }
}