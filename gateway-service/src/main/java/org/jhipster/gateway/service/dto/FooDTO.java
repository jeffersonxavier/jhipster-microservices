package org.jhipster.gateway.service.dto;

public class FooDTO {

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