package org.jhipster.gateway.repository;

import org.jhipster.gateway.domain.Foobar;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Foobar entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FoobarRepository extends JpaRepository<Foobar, Long> {

}
