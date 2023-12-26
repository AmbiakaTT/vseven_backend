package com.vseven.launchpad.repository;

import com.vseven.launchpad.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {

    Optional<Link> findByLinkId(Integer theId);

    @Query(value = "SELECT COUNT(*) FROM Link WHERE section_id IN (:sectionIds)", nativeQuery = true)
    Integer countLinksBySectionIds(@Param("sectionIds") List<Integer> sectionIds);

}