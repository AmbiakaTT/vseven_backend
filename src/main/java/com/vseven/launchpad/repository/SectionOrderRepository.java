package com.vseven.launchpad.repository;

import com.vseven.launchpad.entity.SectionOrder;
import com.vseven.launchpad.entity.UserQuickLink;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SectionOrderRepository extends JpaRepository<SectionOrder, Integer> {

    List<SectionOrder> findByUserUserName(String theUserName);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO SectionOrder (user_id, section_id, section_order) VALUES (:userId, :sectionId, :sectionOrder) " +
            "ON DUPLICATE KEY UPDATE section_order = VALUES(section_order)", nativeQuery = true)
    void saveSectionOrderNativeQuery(Integer userId, Integer sectionId, Integer sectionOrder);
    
}
