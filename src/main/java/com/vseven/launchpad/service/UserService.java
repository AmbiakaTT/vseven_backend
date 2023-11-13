package com.vseven.launchpad.service;

import com.vseven.launchpad.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<User> findAll();
    void save(User theUser);
    User findByUserName(String username);
    void deleteById (Integer theId);

    //boolean existsByUsername(String username);

    boolean existsById(Integer aLong);

    Optional<User> findById(Integer aLong);

    // String findByEmail(String email);
}
