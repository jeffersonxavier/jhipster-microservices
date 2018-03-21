package org.jhipster.bar.service;

import org.jhipster.bar.domain.Bar;
import org.jhipster.bar.repository.BarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing Bar.
 */
@Service
@Transactional
public class BarService {

    private final Logger log = LoggerFactory.getLogger(BarService.class);

    private final BarRepository barRepository;

    public BarService(BarRepository barRepository) {
        this.barRepository = barRepository;
    }

    /**
     * Save a bar.
     *
     * @param bar the entity to save
     * @return the persisted entity
     */
    public Bar save(Bar bar) {
        log.debug("Request to save Bar : {}", bar);
        return barRepository.save(bar);
    }

    /**
     * Get all the bars.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Bar> findAll() {
        log.debug("Request to get all Bars");
        return barRepository.findAll();
    }

    /**
     * Get one bar by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Bar findOne(Long id) {
        log.debug("Request to get Bar : {}", id);
        return barRepository.findOne(id);
    }

    /**
     * Delete the bar by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Bar : {}", id);
        barRepository.delete(id);
    }
}
