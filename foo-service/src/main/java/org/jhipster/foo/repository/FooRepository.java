package org.jhipster.foo.repository;

import org.jhipster.foo.domain.Foo;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Foo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FooRepository extends JpaRepository<Foo, Long> {

}
