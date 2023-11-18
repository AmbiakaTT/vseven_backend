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

    
    //@Query(value = "SELECT * FROM userquicklink WHERE user_name  = :theUserName", nativeQuery = true)
    //public List<UserQuickLink> findByUseruser_name (String user_name);
    List<UserQuickLink> findByUserUserName(String userName);

}
