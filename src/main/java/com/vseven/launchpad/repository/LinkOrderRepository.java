package com.vseven.launchpad.repository;

import com.vseven.launchpad.entity.LinkOrder;
    import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LinkOrderRepository extends JpaRepository<LinkOrder, Integer> {
    List<LinkOrder> findByUserUserName(String theUserName);

}
