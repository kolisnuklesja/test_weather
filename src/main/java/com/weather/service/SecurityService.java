package com.weather.service;

/**
 * Created by Tanya on 01.02.2017.
 */
public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
