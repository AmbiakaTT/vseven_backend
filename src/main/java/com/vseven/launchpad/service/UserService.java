package com.vseven.launchpad.service;

import com.vseven.launchpad.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    void save(User theUser);

    void deleteById (int theId);

    boolean existsByUsername(String username);

    boolean existsById(Long aLong);

    Optional<User> findById(Long aLong);

    // String findByEmail(String email);
}
