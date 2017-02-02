package com.weather.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Tanya on 02.02.2017.
 */
@Entity
@Table(name = "users_requests")
public class UserRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idrequests")
    private Long idrequests;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "idcity")
    private City city;

    @ManyToOne
    @JoinColumn(name = "iduser")
    private User user;

    public Long getIdrequests() {
        return idrequests;
    }

    public void setIdrequests(Long idrequests) {
        this.idrequests = idrequests;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
