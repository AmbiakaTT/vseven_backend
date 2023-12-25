package com.vseven.launchpad.repository;

import com.vseven.launchpad.entity.LinkClick;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface LinkClickRepository extends JpaRepository< LinkClick, Integer> {

    List<LinkClick> findTop5ByOrderByNumOfClicksDesc();

//    Optional<LinkClick> findById(Integer id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO LinkClicks (link_id, num_of_clicks) VALUES (:linkId, :numOfClicks) " +
            "ON DUPLICATE KEY UPDATE num_of_clicks = VALUES(num_of_clicks)", nativeQuery = true)
    void saveLinkClickNativeQuery(Integer linkId, Integer numOfClicks);

    long count();
}
