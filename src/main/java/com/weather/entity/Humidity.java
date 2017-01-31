package com.weather.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Tanya on 26.01.2017.
 */
@XmlRootElement(name = "humidity")
@XmlAccessorType(XmlAccessType.NONE)
public class Humidity {

    @XmlAttribute(name = "value")
    private String value;

    @XmlAttribute(name = "unit")
    private String unit;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}