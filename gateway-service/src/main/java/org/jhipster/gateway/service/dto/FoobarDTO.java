package org.jhipster.gateway.service.dto;

public class FoobarDTO {

    private FooDTO foo;
    private BarDTO bar;

    public FoobarDTO(FooDTO foo, BarDTO bar) {
        this.foo = foo;
        this.bar = bar;
    }

    public BarDTO getBar() {
        return this.bar;
    }

    public void setBar(BarDTO bar) {
        this.bar = bar;
    }

    public FooDTO getFoo() {
        return this.foo;
    }

    public void setFoo(FooDTO foo) {
        this.foo = foo;
    }

    @Override
    public String toString() {
        return "Foobar{" +
            "bar=" + getBar() +
            ", foo=" + getFoo() + "}";
    }
}
