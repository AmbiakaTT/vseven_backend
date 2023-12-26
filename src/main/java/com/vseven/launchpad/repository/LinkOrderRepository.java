package com.vseven.launchpad.repository;

import com.vseven.launchpad.entity.LinkOrder;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LinkOrderRepository extends JpaRepository<LinkOrder, Integer> {
    List<LinkOrder> findByUserUserName(String theUserName);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO LinkOrder (user_id, section_id, link_order, link_id) VALUES (:userId, :sectionId, :linkOrder, :linkId) " +
            "ON DUPLICATE KEY UPDATE link_order = VALUES(link_order)", nativeQuery = true)
    void saveLinkOrderNativeQuery(Integer userId, Integer sectionId, Integer linkOrder, Integer linkId);

    @Transactional
    @Modifying
    @Query(value = "REPLACE INTO LinkOrder SET link_order = :linkOrder, link_id = :linkId " +
            "WHERE user_id = :userId AND section_id = :sectionId", nativeQuery = true)
    void updateLinkOrderNativeQuery(Integer userId, Integer sectionId, Integer linkOrder, Integer linkId);

}
