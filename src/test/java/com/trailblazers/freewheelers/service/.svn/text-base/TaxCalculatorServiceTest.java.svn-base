package com.trailblazers.freewheelers.service;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Country;
import com.trailblazers.freewheelers.model.Item;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TaxCalculatorServiceTest {

    private static final BigDecimal ANY_PRICE = new BigDecimal(10);

    TaxCalculatorService taxCalculatorService = new TaxCalculatorService();

    @Test
    public void shouldReturnVatForUkUser() {
        //given
        Account account = new Account();
        account.setCountry_id(Country.UK_ID);
        Item item = new Item();
        item.setPrice(ANY_PRICE);
        //when
        BigDecimal vat = taxCalculatorService.calculateVat(account, item);
        //then
        assertEquals(vat, new BigDecimal(2));
    }

    @Test
    public void shouldReturnZeroVatForEuExceptUkUser() {
        //given
        Account account = new Account();
        account.setCountry_id(Country.FRANCE_ID);
        Item item = new Item();
        item.setPrice(ANY_PRICE);
        //when
        BigDecimal vat = taxCalculatorService.calculateVat(account, item);
        //then
        assertEquals(vat, new BigDecimal(0));
    }

    @Test
    public void shouldReturnZeroVatForNonEuUser() {
        //given
        Account account = new Account();
        account.setCountry_id(Country.USA_ID);
        Item item = new Item();
        item.setPrice(ANY_PRICE);
        //when
        BigDecimal vat = taxCalculatorService.calculateVat(account, item);
        //then
        assertEquals(vat, new BigDecimal(0));
    }
}
