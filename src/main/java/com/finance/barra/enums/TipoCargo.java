package com.finance.barra.enums;

import lombok.Getter;

@Getter
public enum TipoCargo {
    ADM(1, "ROLE_ADMIN"),
    RH(2, "ROLE_RH"),
    PAGADORES(3, "ROLE_PAGADORES");

    private Integer cod;
    private String desc;

    TipoCargo() {}

    TipoCargo (Integer cod, String desc) {
        this.cod = cod;
        this.desc = desc;
    }
}
