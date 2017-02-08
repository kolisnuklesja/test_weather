package com.weather.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Tanya on 02.02.2017.
 */
@Entity
public class City implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idcity")
    private Long idcity;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Weather> weather;


    public Long getIdcity() {
        return idcity;
    }

    public void setIdcity(Long idcity) {
        this.idcity = idcity;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
