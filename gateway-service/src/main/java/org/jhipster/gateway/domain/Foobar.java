package org.jhipster.gateway.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Foobar.
 */
@Entity
@Table(name = "foobar")
public class Foobar implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "foobar_name")
    private String foobarName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFoobarName() {
        return foobarName;
    }

    public Foobar foobarName(String foobarName) {
        this.foobarName = foobarName;
        return this;
    }

    public void setFoobarName(String foobarName) {
        this.foobarName = foobarName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Foobar foobar = (Foobar) o;
        if (foobar.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), foobar.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Foobar{" +
            "id=" + getId() +
            ", foobarName='" + getFoobarName() + "'" +
            "}";
    }
}
