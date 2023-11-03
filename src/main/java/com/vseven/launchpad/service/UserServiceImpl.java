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

    //private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
       // String New_password = passwordEncoder.encode(theUser.getPassword());
       // theUser.setPassword(theUser.getPassword());
        userRepository.save(theUser);

    }
    @Override
    public void deleteById(int theId) {
        userRepository.deleteById((long) theId);
    }


    @Override
    public boolean existsByUsername(String username) {
        // Use the existsByUsername method from the repository
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsById(Long aLong) {
        return userRepository.existsById(aLong);
    }

    @Override
    public Optional<User> findById(Long aLong) {
        return  userRepository.findById(aLong);
    }

    /*@Override
    public String findByEmail (String email) {
        return userRepository.findByEmail(email);
    }       */

}
