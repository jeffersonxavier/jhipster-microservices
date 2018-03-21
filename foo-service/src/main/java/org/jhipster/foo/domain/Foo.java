package org.jhipster.foo.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Foo.
 */
@Entity
@Table(name = "foo")
public class Foo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "foo_name")
    private String fooName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFooName() {
        return fooName;
    }

    public Foo fooName(String fooName) {
        this.fooName = fooName;
        return this;
    }

    public void setFooName(String fooName) {
        this.fooName = fooName;
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
        Foo foo = (Foo) o;
        if (foo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), foo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Foo{" +
            "id=" + getId() +
            ", fooName='" + getFooName() + "'" +
            "}";
    }
}
