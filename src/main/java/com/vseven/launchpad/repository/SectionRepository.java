package com.vseven.launchpad.repository;


import com.vseven.launchpad.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer> {

    @Override
    Optional<Section> findById(Integer integer);

    // Method to count the number of records in the Section table
    long count();
}