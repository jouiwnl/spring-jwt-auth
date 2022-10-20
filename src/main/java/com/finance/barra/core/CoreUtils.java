package com.finance.barra.core;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CoreUtils {

    public <T> BigDecimal converToBigDecimal(T number) {
        return (BigDecimal) number;
    }

}
