package com.weather.entity;

import javax.xml.bind.annotation.*;

/**
 * Created by Tanya on 27.01.2017.
 */
@XmlRootElement(name = "time")
@XmlAccessorType(XmlAccessType.NONE)
public class Time {

    @XmlAttribute(name = "from")
    private String from;

    @XmlAttribute(name = "to")
    private String to;

    @XmlElement(name = "temperature")
    private Temperature temperature;

    @XmlElement(name = "windSpeed")
    private WindSpeed windSpeed;

    @XmlElement(name = "windDirection")
    private WindDirection windDirection;

    @XmlElement(name = "humidity")
    private Humidity humidity;

    @XmlElement(name = "clouds")
    private Clouds clouds;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public WindSpeed getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(WindSpeed windSpeed) {
        this.windSpeed = windSpeed;
    }

    public WindDirection getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(WindDirection windDirection) {
        this.windDirection = windDirection;
    }

    public Humidity getHumidity() {
        return humidity;
    }

    public void setHumidity(Humidity humidity) {
        this.humidity = humidity;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }
}