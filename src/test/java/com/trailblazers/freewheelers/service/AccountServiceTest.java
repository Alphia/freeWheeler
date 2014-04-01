package com.trailblazers.freewheelers.service;

import com.trailblazers.freewheelers.mappers.AccountMapper;
import com.trailblazers.freewheelers.mappers.AccountRoleMapper;
import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.AccountRole;
import com.trailblazers.freewheelers.service.validation.AccountValidation;
import functional.com.trailblazers.freewheelers.helpers.SyntaxSugar;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class AccountServiceTest {

    AccountService accountService;

    @Mock
    SqlSession sqlSession;
    @Mock
    AccountMapper accountMapper;
    @Mock
    AccountRoleMapper accountRoleMapper;
    @Mock
    AccountValidation accountValidation;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(sqlSession.getMapper(AccountMapper.class)).thenReturn(accountMapper);
        when(sqlSession.getMapper(AccountRoleMapper.class)).thenReturn(accountRoleMapper);

        accountService = new AccountService(sqlSession, accountValidation);
    }

    @Test
    public void shouldNotCreateAccountWhenThereAreValidationErrors(){
        Account account = getAccountWithErrors();

        HashMap errors = new HashMap<String, String>();
        errors.put("anyError", "anyError");
        given(accountValidation.verifyInputs(account)).willReturn(errors);

        ServiceResult<Account> serviceResult = accountService.createAccount(account);

        verify(accountMapper, never()).insert(account);
        verify(accountRoleMapper, never()).insert(any(AccountRole.class));
        verify(sqlSession, never()).commit();
        assertTrue(serviceResult.hasErrors());
    }

    @Test
    public void shouldCreateAccountWhenThereAreNoValidationErrors(){
        Account account = getAccountWithoutErrors();

        ServiceResult<Account> serviceResult = accountService.createAccount(account);

        verify(accountMapper, times(1)).insert(account);
        verify(accountRoleMapper, times(1)).insert(any(AccountRole.class));
        verify(sqlSession, times(1)).commit();
        assertFalse(serviceResult.hasErrors());
    }

    @Test
    public void shouldReturnAccountRoleForAGivenAccountName() throws Exception {
        Account account = getAccountWithoutErrors();
        accountService.getAccountRoleFor(account);

        verify(accountRoleMapper, times(1)).getByAccountName(account.getAccount_name());
    }

    @Test
    public void shouldReturnAccountRoleForAccount(){
        Account account = getAccountWithoutErrors();
        accountService.createAccount(account);
        AccountRole accountRole = new AccountRole(account.getAccount_name(), "ROLE_USER");
        when(accountRoleMapper.getByAccountName(account.getAccount_name())).thenReturn(accountRole);

        AccountRole accountRoleFor = accountService.getAccountRoleFor(account);

        assertThat(accountRoleFor.getRole(), is("ROLE_USER"));
    }

    private Account getAccountWithErrors() {
        Account account =  new Account();
        account.setAccount_name("");
        account.setEmail_address("");
        account.setPassword("");
        account.setConfirmPassword("");
        account.setPhoneNumber("");
        account.setCountry_id(0);
        return account;
    }

    private Account getAccountWithoutErrors() {
        Account account = new Account();
        account.setEmail_address("example@example.com");
        account.setPassword("eXAmple12#");
        account.setConfirmPassword("eXAmple12#");
        account.setAccount_name("Example Person");
        account.setPhoneNumber(SyntaxSugar.PHONE_NUMBER);
        account.setCountry_id(1);
        return account;
    }
}
