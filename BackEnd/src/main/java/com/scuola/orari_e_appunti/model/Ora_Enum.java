package com.scuola.orari_e_appunti.model;

public enum Ora_Enum {
    H08_09("08:00-9:00"),
    H09_10("09:00-10:00"),
    H10_11("10:00-11:00"),
    H11_12("11:00-12:00"),
    H12_13("12:00-13:00"),
    H13_14("13:00-14:00"),
    H14_15("14:00-15:00");

    private final String label;

    Ora_Enum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }


}