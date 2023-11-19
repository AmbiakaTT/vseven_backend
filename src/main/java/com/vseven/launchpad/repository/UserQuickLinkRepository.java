package com.vseven.launchpad.repository;

import com.vseven.launchpad.entity.UserQuickLink;
import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserQuickLinkRepository extends JpaRepository<UserQuickLink, Integer> {
    List<UserQuickLink> findByUserUserName(String theUserName);
}