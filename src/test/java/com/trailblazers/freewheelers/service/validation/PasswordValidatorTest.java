package com.trailblazers.freewheelers.service.validation;

import com.trailblazers.freewheelers.model.Account;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PasswordValidatorTest {

    private PasswordValidator passwordValidator = new PasswordValidator();
    private Account account;

    @Before
    public void setup() {
        account = new Account();
    }

    @Test
    public void shouldComplainAboutAnEmptyPassword() throws Exception {
        String emptyPassword = "";
        account.setPassword(emptyPassword);
        account.setConfirmPassword(emptyPassword);
        HashMap errors = new HashMap<String, String>();

        passwordValidator.validatePassword(account, errors);

        assertThereIsOneErrorFor("password", "can't be empty", errors);
    }

    @Test
    public void shouldComplainIfPasswordIsInvalid() throws Exception {
        String password = "hello";
        account.setPassword(password);
        account.setConfirmPassword(password);
        HashMap errors = new HashMap<String, String>();

        passwordValidator.validatePassword(account, errors);

        assertThereIsOneErrorFor("validPassword", "Must be between 8-20 characters, and contain at least 1 number, 1 lowercase letter, 1 uppercase letter, 1 special character!", errors);
    }

    @Test
    public void shouldComplainIfConfirmPasswordDoesNotMatchPassword() throws Exception {
        account.setPassword("Secret123!");
        account.setConfirmPassword("DifferentSecret123!");
        HashMap errors = new HashMap<String, String>();

        passwordValidator.validatePassword(account, errors);

        assertThereIsOneErrorFor("confirmPassword", "Confirm password must match password!", errors);
    }

    @Test
    public void shouldComplainIfPasswordIsTooShort() {
        String tooShortPassword = "aaaaS1&";

        account.setPassword(tooShortPassword);
        account.setConfirmPassword(tooShortPassword);
        HashMap errors = new HashMap<String, String>();

        passwordValidator.validatePassword(account, errors);

        assertThereIsOneErrorFor("validPassword", "Must be between 8-20 characters, and contain at least 1 number, 1 lowercase letter, 1 uppercase letter, 1 special character!", errors);
    }

    @Test
    public void shouldComplainIfPasswordIsTooLong() {
        String tooLongPassword = "123456789abcdeABCD&$*";
        account.setPassword(tooLongPassword);
        account.setConfirmPassword(tooLongPassword);
        HashMap errors = new HashMap<String, String>();

        passwordValidator.validatePassword(account, errors);

        assertThereIsOneErrorFor("validPassword", "Must be between 8-20 characters, and contain at least 1 number, 1 lowercase letter, 1 uppercase letter, 1 special character!", errors);
    }

    @Test
    public void shouldComplainIfPasswordDoesNotHaveLowerCaseCharacter() {
        String withoutLowerCasePassword = "123456A&";
        account.setPassword(withoutLowerCasePassword);
        account.setConfirmPassword(withoutLowerCasePassword);
        HashMap errors = new HashMap<String, String>();

        passwordValidator.validatePassword(account, errors);

        assertThereIsOneErrorFor("validPassword", "Must be between 8-20 characters, and contain at least 1 number, 1 lowercase letter, 1 uppercase letter, 1 special character!", errors);
    }

    @Test
    public void shouldComplainIfPasswordDoesNotHaveUpperCaseCharacter() {
        String withoutUpperCasePassword = "abc123$%&";
        account.setPassword(withoutUpperCasePassword);
        account.setConfirmPassword(withoutUpperCasePassword);
        HashMap errors = new HashMap<String, String>();

        passwordValidator.validatePassword(account, errors);

        assertThereIsOneErrorFor("validPassword", "Must be between 8-20 characters, and contain at least 1 number, 1 lowercase letter, 1 uppercase letter, 1 special character!", errors);
    }


    @Test
    public void shouldComplainIfPasswordDoesNotHaveNumber() {
        String withoutNumberPassword = "aaaaaabcXY%";
        account.setPassword(withoutNumberPassword);
        account.setConfirmPassword(withoutNumberPassword);
        HashMap errors = new HashMap<String, String>();

        passwordValidator.validatePassword(account, errors);

        assertThereIsOneErrorFor("validPassword", "Must be between 8-20 characters, and contain at least 1 number, 1 lowercase letter, 1 uppercase letter, 1 special character!", errors);
    }

    @Test
    public void shouldComplainIfPasswordDoesNotHaveSpecialCharacter() {
        String withoutSpecialCharacterPassword = "abc123ABC";
        account.setPassword(withoutSpecialCharacterPassword);
        account.setConfirmPassword(withoutSpecialCharacterPassword);
        HashMap errors = new HashMap<String, String>();

        passwordValidator.validatePassword(account, errors);

        assertThereIsOneErrorFor("validPassword", "Must be between 8-20 characters, and contain at least 1 number, 1 lowercase letter, 1 uppercase letter, 1 special character!", errors);
    }

    private void assertThereIsOneErrorFor(String field, String expected, HashMap<String, String> errors) {
        assertThat(errors.size(), is(1));
        assertThat(errors.get(field), containsString(expected));
    }
}
