package com.weather.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Tanya on 26.01.2017.
 */
@XmlRootElement(name = "clouds")
@XmlAccessorType(XmlAccessType.NONE)
public class Clouds {

    @XmlAttribute(name = "value")
    private String value;

    @XmlAttribute(name = "all")
    private String all;

    @XmlAttribute(name = "unit")
    private String unit;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}