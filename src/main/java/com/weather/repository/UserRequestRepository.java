package com.weather.repository;

import com.weather.model.UserRequest;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Tanya on 02.02.2017.
 */
public interface UserRequestRepository extends CrudRepository<UserRequest, Long> {

}
