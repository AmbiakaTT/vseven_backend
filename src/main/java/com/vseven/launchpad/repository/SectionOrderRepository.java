package com.vseven.launchpad.repository;

import com.vseven.launchpad.entity.SectionOrder;
import com.vseven.launchpad.entity.UserQuickLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionOrderRepository extends JpaRepository<SectionOrder, Integer> {

    List<SectionOrder> findByUserUserName(String theUserName);

}
