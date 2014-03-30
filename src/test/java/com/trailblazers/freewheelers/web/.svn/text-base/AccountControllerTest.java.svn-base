package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.service.impl.AccountService;
import com.trailblazers.freewheelers.service.impl.CountryService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;

public class AccountControllerTest {
    AccountService accountService;
    private HttpServletRequest httpRequest;
    private String email;
    private String name;
    private String password;
    private String phoneNumber;
    private long country_id;

    private AccountController accountController;
    private ModelMap model;
    private CountryService countryService;

    @Before
    public void setUp(){
        initMocks(this);
        accountService = mock(AccountService.class);
        countryService = mock(CountryService.class);

        this.accountController = new AccountController(accountService, countryService);
        model = mock(ExtendedModelMap.class);

        email = "test@test.com";
        name = "Abhishek";
        password = "password";
        phoneNumber = "9876543210";
        country_id = 1;

        httpRequest=mock(HttpServletRequest.class);

        given(httpRequest.getParameter("email")).willReturn(email);
        given(httpRequest.getParameter("name")).willReturn(name);
        given(httpRequest.getParameter("password")).willReturn(password);
        given(httpRequest.getParameter("phoneNumber")).willReturn(phoneNumber);
        given(httpRequest.getParameter("country")).willReturn(String.valueOf(country_id));
    }

    @Test
    public void shouldReturnModelAndViewWithValidationMessage() throws Exception {
        ModelAndView returnedPath = accountController.createAccountForm(model);
        assertEquals(returnedPath.getModel().get("validationMessage"), model);
    }

    @Test
    public void shouldAddCountriesToModelAndView() throws Exception {
        accountController.createAccountForm(model);
        verify(countryService).findAll();
    }

    @Test
    public void shouldCheckTheInputFromTheJSPPage() throws Exception {
        accountController = spy(new AccountController(accountService, countryService));
        accountController.generateCreateAccountForm(httpRequest);
        verify(accountController).makeAccount(httpRequest);
    }

    @Test
    public void shouldMakeAnAccountObject() {
        assertEquals(accountController.makeAccount(httpRequest), new Account(name, password, true, email, phoneNumber, country_id, password));
    }
}
