package com.weather.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Tanya on 02.02.2017.
 */
@Entity
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idweather")
    private Long idweather;

    @ManyToOne
    @JoinColumn(name = "idcity")
    private City city;

    @Column(name = "clouds_name")
    private String cloudsName;

    @Column(name = "clouds_value")
    private int cloudsValue;

    @Column(name = "wind_speed_value")
    private double windSpeedValue;

    @Column(name = "wind_speed_name")
    private String windSpeedName;

    @Column(name = "temperature_value")
    private double temperatureValue;

    @Column(name = "temperature_min")
    private double temperatureMin;

    @Column(name = "temperature_max")
    private double temperatureMax;

    @Column(name = "time_from")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeFrom;

    @Column(name = "time_to")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeTo;

    @Column(name = "humidity_value")
    private int humidityValue;

    @Column(name = "wind_direction")
    private String windDirection;

    public Long getIdweather() {
        return idweather;
    }

    public void setIdweather(Long idweather) {
        this.idweather = idweather;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getCloudsName() {
        return cloudsName;
    }

    public void setCloudsName(String cloudsName) {
        this.cloudsName = cloudsName;
    }

    public int getCloudsValue() {
        return cloudsValue;
    }

    public void setCloudsValue(int cloudsValue) {
        this.cloudsValue = cloudsValue;
    }

    public double getWindSpeedValue() {
        return windSpeedValue;
    }

    public void setWindSpeedValue(double windSpeedValue) {
        this.windSpeedValue = windSpeedValue;
    }

    public String getWindSpeedName() {
        return windSpeedName;
    }

    public void setWindSpeedName(String windSpeedName) {
        this.windSpeedName = windSpeedName;
    }

    public double getTemperatureValue() {
        return temperatureValue;
    }

    public void setTemperatureValue(double temperatureValue) {
        this.temperatureValue = temperatureValue;
    }

    public double getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public Date getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Date timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Date getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Date timeTo) {
        this.timeTo = timeTo;
    }

    public int getHumidityValue() {
        return humidityValue;
    }

    public void setHumidityValue(int humidityValue) {
        this.humidityValue = humidityValue;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }
}
