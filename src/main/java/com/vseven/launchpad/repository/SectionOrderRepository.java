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
    @Query(value = "INSERT INTO SectionOrder (user_id, section_id, section_index, section_column) VALUES (?1, ?2, ?3, ?4) " +
            "ON DUPLICATE KEY UPDATE section_index = VALUES(section_index), section_column = VALUES(section_column)", nativeQuery = true)
    void saveSectionOrderNativeQuery(Integer userId, Integer sectionId, Integer sectionIndex, Integer sectionColumn);


    @Transactional
    @Modifying
    @Query(value = "UPDATE SectionOrder SET section_index = :sectionIndex, section_column = :sectionColumn " +
            "WHERE user_id = :userId AND section_id = :sectionId", nativeQuery = true)
    void updateSectionOrderNativeQuery(Integer userId, Integer sectionId, Integer sectionIndex, Integer sectionColumn);

}
