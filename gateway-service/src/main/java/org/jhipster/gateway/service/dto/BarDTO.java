package org.jhipster.gateway.service.dto;

public class BarDTO {

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
