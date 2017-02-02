package com.weather.service.impl;

import com.weather.model.UserRequest;
import com.weather.repository.UserRequestRepository;
import com.weather.service.UserRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Tanya on 02.02.2017.
 */
@Service
public class UserRequestServiceImpl implements UserRequestService {

    @Autowired
    private UserRequestRepository userRequestRepository;

    @Override
    public void save(UserRequest userRequest) {
        userRequestRepository.save(userRequest);
    }
}
