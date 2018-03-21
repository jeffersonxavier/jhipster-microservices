package org.jhipster.gateway.domain;

import java.io.Serializable;

public class Bar implements Serializable {

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
}
