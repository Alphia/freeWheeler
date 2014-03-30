package com.trailblazers.freewheelers.model;

import com.trailblazers.freewheelers.service.impl.CountryService;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AccountTest {

    private Account account;
    private CountryService countryServiceMock;

    @Before
    public void setUp() throws Exception {
        this.countryServiceMock = mock(CountryService.class);
        account = new Account(countryServiceMock)
                .setAccount_name("Bob")
                .setPassword("password")
                .setConfirmPassword("password")
                .setEmail_address("foo@bar.com")
                .setPhoneNumber("123443245")
                .setCountry_id(1);
    }

    @Test
    public void testCreatingNewAccounts() throws Exception {
        assertThat(account.getAccount_name(), is("Bob"));
        assertThat(account.getPassword(), is("password"));
        assertThat(account.getPassword(), is("password"));
        assertThat(account.getEmail_address(), is("foo@bar.com"));
        assertThat(account.getPhoneNumber(), is("123443245"));
        assertThat(account.getCountry_id(),is(1L));
    }

    @Test
    public void shouldReturnCountry() {
        account.getCountry();
        verify(countryServiceMock).get(1L);
    }
}
