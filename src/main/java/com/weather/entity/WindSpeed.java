package com.weather.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Tanya on 26.01.2017.
 */
@XmlRootElement(name = "windSpeed")
@XmlAccessorType(XmlAccessType.NONE)
public class WindSpeed {

    @XmlAttribute(name = "mps")
    private String mps;

    @XmlAttribute(name = "name")
    private String name;

    public String getMps() {
        return mps;
    }

    public void setMps(String mps) {
        this.mps = mps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}