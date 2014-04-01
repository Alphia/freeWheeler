package com.trailblazers.freewheelers.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AccountTest {

    private Account account;

    @Before
    public void setUp() throws Exception {
        account = new Account()
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
        assertThat(account.getCountry_id(), is(1L));
    }
}
