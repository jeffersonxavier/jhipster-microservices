package org.jhipster.bar.repository;

import org.jhipster.bar.domain.Bar;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Bar entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BarRepository extends JpaRepository<Bar, Long> {

}
