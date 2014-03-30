package com.trailblazers.freewheelers.service;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Item;

import java.math.BigDecimal;

public class TaxCalculatorService {

    private static final double VAT_PERCENTAGE = 0.2;

    public BigDecimal calculateVat(Account account, Item item) {
        if (VatRegion.isUk(account.getCountry_id()))
            return new BigDecimal(item.getPrice().doubleValue() * VAT_PERCENTAGE);
        return new BigDecimal(0);
    }
}
