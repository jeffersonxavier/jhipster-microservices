package org.jhipster.gateway.web.rest;

import com.codahale.metrics.annotation.Timed;

import org.jhipster.gateway.client.BarClient;
import org.jhipster.gateway.client.FooClient;
import org.jhipster.gateway.domain.Foobar;
import org.jhipster.gateway.service.FoobarService;
import org.jhipster.gateway.service.dto.BarDTO;
import org.jhipster.gateway.service.dto.FooDTO;
import org.jhipster.gateway.web.rest.errors.BadRequestAlertException;
import org.jhipster.gateway.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Foobar.
 */
@RestController
@RequestMapping("/api")
public class FoobarResource {

    private final Logger log = LoggerFactory.getLogger(FoobarResource.class);

    private static final String ENTITY_NAME = "foobar";

    private final FoobarService foobarService;

    private BarClient barClient;
    private FooClient fooClient;

    public FoobarResource(FoobarService foobarService, BarClient barClient, FooClient fooClient) {
        this.foobarService = foobarService;
        this.barClient = barClient;
        this.fooClient = fooClient;
    }

    /**
     * POST  /foobars : Create a new foobar.
     *
     * @param foobar the foobar to create
     * @return the ResponseEntity with status 201 (Created) and with body the new foobar, or with status 400 (Bad Request) if the foobar has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/foobars")
    @Timed
    public ResponseEntity<Foobar> createFoobar(@RequestBody Foobar foobar) throws URISyntaxException {
        log.debug("REST request to save Foobar : {}", foobar);
        if (foobar.getId() != null) {
            throw new BadRequestAlertException("A new foobar cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Foobar result = foobarService.save(foobar);
        return ResponseEntity.created(new URI("/api/foobars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /foobars : Updates an existing foobar.
     *
     * @param foobar the foobar to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated foobar,
     * or with status 400 (Bad Request) if the foobar is not valid,
     * or with status 500 (Internal Server Error) if the foobar couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/foobars")
    @Timed
    public ResponseEntity<Foobar> updateFoobar(@RequestBody Foobar foobar) throws URISyntaxException {
        log.debug("REST request to update Foobar : {}", foobar);
        if (foobar.getId() == null) {
            return createFoobar(foobar);
        }
        Foobar result = foobarService.save(foobar);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, foobar.getId().toString()))
            .body(result);
    }

    /**
     * GET  /foobars : get all the foobars.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of foobars in body
     */
    @GetMapping("/foobars")
    @Timed
    public List<Foobar> getAllFoobars() {
        log.debug("REST request to get all Foobars");

        List<BarDTO> bars = barClient.findAll();
        log.debug("========================");
        log.debug("All bars {}", bars);

        List<FooDTO> foos = fooClient.findAll();
        log.debug("========================");
        log.debug("All foos {}", foos);

        return foobarService.findAll();
    }

    /**
     * GET  /foobars/:id : get the "id" foobar.
     *
     * @param id the id of the foobar to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the foobar, or with status 404 (Not Found)
     */
    @GetMapping("/foobars/{id}")
    @Timed
    public ResponseEntity<Foobar> getFoobar(@PathVariable Long id) {
        log.debug("REST request to get Foobar : {}", id);
        Foobar foobar = foobarService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(foobar));
    }

    /**
     * DELETE  /foobars/:id : delete the "id" foobar.
     *
     * @param id the id of the foobar to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/foobars/{id}")
    @Timed
    public ResponseEntity<Void> deleteFoobar(@PathVariable Long id) {
        log.debug("REST request to delete Foobar : {}", id);
        foobarService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
