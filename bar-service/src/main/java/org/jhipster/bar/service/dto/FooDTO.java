package org.jhipster.bar.service.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FooDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String fooName;

    /**
     * @return the fooName
     */
    public String getFooName() {
        return fooName;
    }

    /**
     * @param fooName the fooName to set
     */
    public void setFooName(String fooName) {
        this.fooName = fooName;
    }

    @Override
    public String toString() {
        return "Foo{" +
            "fooName=" + getFooName() + "}";
    }
}
