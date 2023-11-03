package com.vseven.launchpad.service;

import com.vseven.launchpad.entity.Link;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;





public interface LinkService {
    List<Link> findAll();
    void save(Link theLink);

    void deleteById (Long theId);

    boolean existsById(Long id);

    Optional<Link> findById(Long id);
}
