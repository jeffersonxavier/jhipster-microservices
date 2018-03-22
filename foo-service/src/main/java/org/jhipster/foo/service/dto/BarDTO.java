package org.jhipster.foo.service.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class BarDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String barName;

    /**
     * @return the barName
     */
    public String getBarName() {
        return barName;
    }

    /**
     * @param barName the barName to set
     */
    public void setBarName(String barName) {
        this.barName = barName;
    }

    @Override
    public String toString() {
        return "Bar{" +
            "barName=" + getBarName() + "}";
    }
}
