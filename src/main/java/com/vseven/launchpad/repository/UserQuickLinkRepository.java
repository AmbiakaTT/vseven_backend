package com.vseven.launchpad.repository;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.vseven.launchpad.entity.User;
import com.vseven.launchpad.entity.UserQuickLink;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserQuickLinkRepository extends JpaRepository<UserQuickLink, Integer> {
    List<UserQuickLink> findByUserUserName(String theUserName);

    @Query(value = "SELECT * FROM UserQuickLink " +
            "where user_name= ?1 " +
            "AND link_id= ?2", nativeQuery = true)
    Optional<UserQuickLink> findByUserNameAndLinkId(String username, Integer linkId);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM UserQuickLink " +
            "where user_name= ?1 " +
            "AND link_id= ?2", nativeQuery = true)
    void deleteByUserNameAndLinkId(String username, Integer linkId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM UserQuickLink " +
            "WHERE user_name= ?1 " +
            "AND link_id IN (?2)", nativeQuery = true)
    void deleteByUserNameAndLinkIdsNativeQuery(String a, List<Integer> b);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM UserQuickLink " +
            "WHERE user_name= ?1 ", nativeQuery = true)
    void resetUserQuickLinkNativeQuery(String username);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO UserQuickLink " +
            "WHERE user_name= ?1 " +
            "AND link_id IN (?2)", nativeQuery = true)
    void insertByUserNameAndLinkIdsNativeQuery(String a, List<Integer> b);
}