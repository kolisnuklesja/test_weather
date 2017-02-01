package com.weather.repository;

import com.weather.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Tanya on 01.02.2017.
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}
