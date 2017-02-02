package com.weather.service;

import com.weather.model.User;

/**
 * Created by Tanya on 01.02.2017.
 */
public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
