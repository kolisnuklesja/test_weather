package com.weather.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Tanya on 27.01.2017.
 */
@XmlRootElement(name = "forecast")
@XmlAccessorType(XmlAccessType.NONE)
public class Forecast {

    @XmlElement(name = "time")
    private List<Time> timeList;

    public List<Time> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<Time> timeList) {
        this.timeList = timeList;
    }
}