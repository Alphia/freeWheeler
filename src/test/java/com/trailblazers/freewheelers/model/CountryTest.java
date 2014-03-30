package com.trailblazers.freewheelers.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CountryTest {
    private Country country;

    @Before
    public void setUp() throws Exception {
        country = new Country("USA");
    }

    @Test
    public void testCreatingNewAccounts() throws Exception {
        assertThat(country.getCountry_name(), is("USA"));
    }
}
