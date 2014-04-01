package com.trailblazers.freewheelers.service;

import com.trailblazers.freewheelers.mappers.AccountMapper;
import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.AccountRole;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by jpramos on 3/28/14.
 */
public class UserDetailsServiceImplTest {

    private Account account;

    @Mock
    private AccountService accountService;

    @Mock
    AccountMapper accountMapper;
    private final String ACCOUNT_NAME = "Ralph";
    private final String ACCOUNT_PASSWORD = "H3!!o123";
    private final String ACCOUNT_ROLE = "ROLE_USER";
    private UserDetailsService userDetailsService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        this.account = new Account();
        account.setAccount_name(ACCOUNT_NAME);
        account.setPassword(ACCOUNT_PASSWORD);

        when(accountService.getAccountByName(ACCOUNT_NAME)).thenReturn(account);
        when(accountService.getAccountRoleFor(account)).thenReturn(new AccountRole(ACCOUNT_NAME, ACCOUNT_ROLE));

        this.userDetailsService = new UserDetailsServiceImpl(accountService);
    }

    @Test
    public void shouldPullAccountBasedOnAccountName(){
        userDetailsService.loadUserByUsername(ACCOUNT_NAME);

        verify(accountService, times(1)).getAccountByName(ACCOUNT_NAME);
    }

    @Test
    public void shouldReturnUserDetailsWithTheProperInformation(){
        when(accountMapper.getByName(ACCOUNT_NAME)).thenReturn(account);
        String accountName = account.getAccount_name();
        UserDetails userDetails = userDetailsService.loadUserByUsername(accountName);

        assertEquals(userDetails.getUsername(), (ACCOUNT_NAME));
        assertEquals(userDetails.getPassword(), (ACCOUNT_PASSWORD));
    }

    @Test
    public void shouldReturnUserDetailsWithCorrectRole() throws Exception {

        UserDetails userDetails = userDetailsService.loadUserByUsername(ACCOUNT_NAME);

        ArrayList<? extends GrantedAuthority> authorities = (ArrayList<? extends GrantedAuthority>) userDetails.getAuthorities();

        assertEquals(authorities.get(0).getAuthority(), "ROLE_USER");
    }
}
