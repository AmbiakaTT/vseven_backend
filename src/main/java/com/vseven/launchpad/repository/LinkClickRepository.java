package com.vseven.launchpad.repository;

import com.vseven.launchpad.entity.LinkClick;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LinkClickRepository extends JpaRepository< LinkClick, Integer> {

    List<LinkClick> findTop5ByOrderByNumOfClicksDesc();
}
