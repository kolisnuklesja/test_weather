package com.weather.dto;


import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * Created by Tanya on 02.02.2017.
 */

public class WeatherDTO {


    private String cloudsName;
    private String windSpeedName;
    private String windDirection;

    @NotNull(message = "Should not to be null")
    @DecimalMax(value = "50", message = "Max value is 50")
    @DecimalMin(value = "-50", message = "Min value is -50")
    private Integer tempretureValue;

    @NotEmpty(message = "Should not to be null")
    private String time;

    @NotNull(message = "Should not to be null")
    @DecimalMax(value = "100", message = "Max value is 100")
    @DecimalMin(value = "0", message = "Min value is 0")
    private Integer humidity;

    public String getCloudsName() {
        return cloudsName;
    }

    public void setCloudsName(String cloudsName) {
        this.cloudsName = cloudsName;
    }

    public String getWindSpeedName() {
        return windSpeedName;
    }

    public void setWindSpeedName(String windSpeedName) {
        this.windSpeedName = windSpeedName;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public Integer getTempretureValue() {
        return tempretureValue;
    }

    public void setTempretureValue(Integer tempretureValue) {
        this.tempretureValue = tempretureValue;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }
}
