package com.vseven.launchpad.service;

import com.vseven.launchpad.entity.Link;
import com.vseven.launchpad.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LinkServiceImpl implements LinkService{



    private final LinkRepository linkRepository;


    @Autowired
    public LinkServiceImpl(LinkRepository thelinkRepository){
        linkRepository = thelinkRepository;
    }
    
    @Override
    public List<Link> findAll() {
        return linkRepository.findAll();
    }

    @Override
    public void save(Link theLink) {
        linkRepository.save(theLink);
    }

    @Override
    public void deleteById(Long theId) {

        linkRepository.deleteById(theId);

    }

    @Override
    public boolean existsById(Long id) {
        return linkRepository.existsById(id);
    }

    @Override
    public Optional<Link> findById(Long id) {
        return linkRepository.findById(id);
    }
}
