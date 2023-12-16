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
            "ON DUPLICATE KEY UPDATE link_id = VALUES(link_id)", nativeQuery = true)
    void saveLinkOrderNativeQuery(Integer userId, Integer sectionId, Integer linkOrder, Integer linkId);

}
