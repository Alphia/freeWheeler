package com.trailblazers.freewheelers.service.validation;

import com.trailblazers.freewheelers.model.Account;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class AccountValidationTest {

    public static final String SOME_EMAIL = "guenter.grass@gmail.com";
    public static final String SOME_PASSWORD = "V3ry Secure!";
    public static final String SOME_NAME = "Günter Grass";
    public static final String SOME_PHONE = "945542741";
    private final String PHONE_NUMBER_ERROR = "Phone number should contain only digits and must be between 6-12 digits long";
    private Account account;
    private AccountValidation accountValidation;
    public static final long SOME_COUNTRY_ID=3;

    @Before
    public void setup() {
        account = new Account()
                .setEmail_address(SOME_EMAIL)
                .setPassword(SOME_PASSWORD)
                .setConfirmPassword(SOME_PASSWORD)
                .setAccount_name(SOME_NAME)
                .setPhoneNumber(SOME_PHONE)
                .setEnabled(true)
                .setCountry_id(SOME_COUNTRY_ID);

        accountValidation = new AccountValidation(new PasswordValidator());
    }

    @Test
    public void shouldHaveNoErrorsForValidInput() throws Exception {
        HashMap errors = accountValidation.verifyInputs(account);

        assertThat(errors.size(), is(0));
    }
    
    @Test
    public void shouldComplainAboutAnInvalidEmail() throws Exception {
        String invalidEmail = "invalid.email.address";
        account.setEmail_address(invalidEmail);

        HashMap errors = accountValidation.verifyInputs(account);

        assertThereIsOneErrorFor("email", "enter a valid email", errors);
    }

    @Test
    public void shouldComplainAboutAnEmptyName() throws Exception {
        String emptyName = "";

        account.setAccount_name(emptyName);

        HashMap errors = accountValidation.verifyInputs(account);

        assertThereIsOneErrorFor("name", "enter a name", errors);
    }

    @Test
    public void shouldComplainAboutAnEmptyPhoneNumber() throws Exception {
        String emptyPhoneNumber = "";

        account.setPhoneNumber(emptyPhoneNumber);

        HashMap errors = accountValidation.verifyInputs(account);

        assertThereIsOneErrorFor("phoneNumber", PHONE_NUMBER_ERROR, errors);
    }

    @Test
    public void shouldComplainIfThePhoneNumberContainsNonDigitCharacters() throws Exception {
        String phoneNumber = "123$45678";

        account.setPhoneNumber(phoneNumber);

        HashMap errors = accountValidation.verifyInputs(account);

        assertThereIsOneErrorFor("phoneNumber", PHONE_NUMBER_ERROR, errors);
    }

    @Test
    public void shouldComplainIfAPhoneNumberHasLessThan6Digits() throws Exception {
        String tooShortPhoneNumber = "12345";

        account.setPhoneNumber(tooShortPhoneNumber);
        HashMap errors = accountValidation.verifyInputs(account);

        assertThereIsOneErrorFor("phoneNumber", PHONE_NUMBER_ERROR, errors);
    }

    @Test
    public void shouldComplainIfThePhoneNumberHasMoreThan12Digits() throws Exception {
        String tooLongPhoneNumber = "1234567891234";

        account.setPhoneNumber(tooLongPhoneNumber);
        HashMap errors = accountValidation.verifyInputs(account);

        assertThereIsOneErrorFor("phoneNumber", PHONE_NUMBER_ERROR, errors);
    }

    @Test
    public void shouldSuccessfullyValidateAPhoneNumberWith8Digits() throws Exception {
        String correctLengthPhoneNumber = "12345678";

        account.setPhoneNumber(correctLengthPhoneNumber);
        HashMap errors = accountValidation.verifyInputs(account);

        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldComplainAboutZeroForCountryId() throws Exception {
        long country = 0;

        account.setCountry_id(country);

        HashMap errors = accountValidation.verifyInputs(account);

        assertThereIsOneErrorFor("country", "enter a country name", errors);
    }

    @Test
    public void shouldComplainIfEmailAddressIsAlreadyUsed() throws Exception {
        String usedEmailAddress = "user@freewheelers.com";
        account.setEmail_address(usedEmailAddress);

        HashMap errors = accountValidation.verifyInputs(account);

        assertThereIsOneErrorFor("email","Email is already in use. Please enter a different email address", errors);
    }

    private void assertThereIsOneErrorFor(String field, String expected, HashMap<String, String> errors) {
        assertThat(errors.size(), is(1));
        assertThat(errors.get(field), containsString(expected));
    }
}
