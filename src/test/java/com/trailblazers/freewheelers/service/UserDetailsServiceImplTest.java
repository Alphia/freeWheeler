package com.trailblazers.freewheelers.service;

import com.trailblazers.freewheelers.mappers.AccountMapper;
import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.service.UserDetailsServiceImpl;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by jpramos on 3/28/14.
 */
public class UserDetailsServiceImplTest {

    private Account account;

    @Mock
    SqlSession sqlSession;

    @Mock
    AccountMapper accountMapper;
    private final String ACCOUNT_NAME = "Ralph";
    private final String ACCOUNT_PASSWORD = "H3!!o123";
    private UserDetailsService userDetailsService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(sqlSession.getMapper(AccountMapper.class)).thenReturn(accountMapper);

        this.account = new Account();
        account.setAccount_name(ACCOUNT_NAME);
        account.setPassword(ACCOUNT_PASSWORD);

        this.userDetailsService = new UserDetailsServiceImpl(sqlSession);
    }

    @Test
    public void shouldPullAccountBasedOnAccountName(){
        userDetailsService.loadUserByUsername(ACCOUNT_NAME);

        verify(accountMapper, times(1)).getByName(ACCOUNT_NAME);
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
