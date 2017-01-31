package com.weather.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Tanya on 26.01.2017.
 */

@XmlRootElement(name = "temperature")
@XmlAccessorType(XmlAccessType.NONE)
public class Temperature {

    @XmlAttribute(name = "unit")
    private String unit;

    @XmlAttribute(name = "value")
    private String value;

    @XmlAttribute(name = "min")
    private String min;

    @XmlAttribute(name = "max")
    private String max;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }
}