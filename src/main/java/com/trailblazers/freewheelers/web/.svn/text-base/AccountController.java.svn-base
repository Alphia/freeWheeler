package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.mappers.MyBatisUtil;
import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.service.ServiceResult;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.CountryService;
import com.trailblazers.freewheelers.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private CountryService countryService;

    @Autowired
    public AccountController(AccountService accountService, CountryService countryService) {
        this.accountService = accountService;
        this.countryService = countryService;
    }

    @RequestMapping(value = {"/create"}, method = RequestMethod.GET)
    public ModelAndView createAccountForm(ModelMap model) {
        List countries = countryService.findAll();
        model.put("countries", countries);
        return new ModelAndView("account/create", "validationMessage", model);
    }

    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public ModelAndView generateCreateAccountForm(HttpServletRequest request) throws IOException {
        Account account = makeAccount(request);
        try {
            ServiceResult<Account> result = accountService.createAccount(account);
            if (result.hasErrors()) {
                return showAccountCreationErrors(result.getErrors(), account);
            }
            return showAccountCreationSuccess(result.getModel());
        } catch (Exception e) {
            return showAccountCreationFailureError();
        }
    }

    private ModelAndView showAccountCreationErrors(Map errors, Account account) {
        ModelMap model = new ModelMap();
        model.put("errors", errors);
        model.put("userDetails", account);

        List countries = countryService.findAll();
        model.put("countries", countries);

        return new ModelAndView("account/create", "validationMessage", model);
    }

    private ModelAndView showAccountCreationFailureError() {
        return new ModelAndView("account/createFailure");
    }

    private ModelAndView showAccountCreationSuccess(Account account) {

        logUserIn(account);

        ModelMap model = new ModelMap();
        model.put("name", account.getAccount_name());
        return new ModelAndView("account/createSuccess", "postedValues", model);
    }

    private void logUserIn(Account account) {
        UserDetailsService userDetailsService = new UserDetailsServiceImpl(MyBatisUtil.getSqlSessionFactory().openSession());
        UserDetails userDetails = userDetailsService.loadUserByUsername(account.getAccount_name());
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, account.getPassword(), userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    public Account makeAccount(HttpServletRequest httpRequest) {
        String email = httpRequest.getParameter("email");
        String password = httpRequest.getParameter("password");
        String confirmPassword = httpRequest.getParameter("confirmPassword");
        String name = httpRequest.getParameter("name");
        String phoneNumber = httpRequest.getParameter("phoneNumber");
        long country_id = Long.parseLong(httpRequest.getParameter("country"));

        return new Account(name, password, true, email, phoneNumber, country_id, confirmPassword);
    }
}
