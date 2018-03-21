package org.jhipster.gateway.service;

import org.jhipster.gateway.domain.Foobar;
import org.jhipster.gateway.repository.FoobarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing Foobar.
 */
@Service
@Transactional
public class FoobarService {

    private final Logger log = LoggerFactory.getLogger(FoobarService.class);

    private final FoobarRepository foobarRepository;

    public FoobarService(FoobarRepository foobarRepository) {
        this.foobarRepository = foobarRepository;
    }

    /**
     * Save a foobar.
     *
     * @param foobar the entity to save
     * @return the persisted entity
     */
    public Foobar save(Foobar foobar) {
        log.debug("Request to save Foobar : {}", foobar);
        return foobarRepository.save(foobar);
    }

    /**
     * Get all the foobars.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Foobar> findAll() {
        log.debug("Request to get all Foobars");
        return foobarRepository.findAll();
    }

    /**
     * Get one foobar by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Foobar findOne(Long id) {
        log.debug("Request to get Foobar : {}", id);
        return foobarRepository.findOne(id);
    }

    /**
     * Delete the foobar by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Foobar : {}", id);
        foobarRepository.delete(id);
    }
}
