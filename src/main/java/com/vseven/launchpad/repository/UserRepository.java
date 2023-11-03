package com.vseven.launchpad.repository;

import com.vseven.launchpad.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    // You can add custom query methods here if needed
}