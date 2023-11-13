package com.vseven.launchpad.service;

import com.vseven.launchpad.entity.User;
import com.vseven.launchpad.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    @Autowired
    public UserServiceImpl (UserRepository theuserRepository ) {
        userRepository = theuserRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(User theUser)  {
        userRepository.save(theUser);

    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public void deleteById(Integer theId) {
        userRepository.deleteById(Long.valueOf(theId));
    }
    @Override
    public boolean existsById(Integer aLong) {
        return userRepository.existsById(Long.valueOf(aLong));
    }

    @Override
    public Optional<User> findById(Integer aLong) {
        return  userRepository.findById(Long.valueOf(aLong));
    }

}
