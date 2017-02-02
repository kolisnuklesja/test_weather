package com.weather.service;

import com.weather.model.Role;
import com.weather.model.User;
import com.weather.repository.RoleRepository;
import com.weather.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanya on 01.02.2017.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        List<Role> roleList = new ArrayList<>();
        roleList.add(roleRepository.findByName("ROLE_USER"));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleList);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}