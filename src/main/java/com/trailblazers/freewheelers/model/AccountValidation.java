package com.trailblazers.freewheelers.model;

import java.util.HashMap;

public class AccountValidation {

    private final int MAX_PHONE_NUMBER_DIGITS = 12;
    private final int MIN_PHONE_NUMBER_DIGITS = 6;

    public HashMap<String, String> verifyInputs(Account account) {
        HashMap<String, String> errors = new HashMap<String, String>();

        validateEmail(account, errors);
        validatePassword(account, errors);
        validateName(account, errors);
        validatePhoneNumber(account, errors);
        validateCountry(account, errors);

        return errors;
    }

    private void validateEmail(Account account, HashMap<String, String> errors) {
        if (!account.getEmail_address().contains("@")) {
            errors.put("email", "Must enter a valid email!");
        }
    }

    private void validateName(Account account, HashMap<String, String> errors) {
        if(account.getAccount_name().isEmpty()) {
            errors.put("name", "Must enter a name!");
        }
    }

    private void validateCountry(Account account, HashMap<String, String> errors) {
        if (account.getCountry_id() == 0) {
            errors.put("country", "Must enter a country name!");
        }
    }

    private void validatePassword(Account account, HashMap<String, String> errors) {
        if (account.getPassword().isEmpty()) {
            errors.put("password", "Password can't be empty");
        }
        else if(!hasNecessaryCharacters(account.getPassword())) {
            errors.put("validPassword", "Must enter a valid password!");
        }

        if(!account.getConfirmPassword().equals(account.getPassword())){
            errors.put("confirmPassword", "Confirm password must match password!");
        }
    }

    private boolean hasNecessaryCharacters(String password) {
        boolean hasUppercaseAndLowerCase = !password.equals(password.toLowerCase()) && !password.equals(password.toUpperCase());
        boolean isBetween8And20Characters = password.length() >= 8 && password.length() <= 20;
        boolean hasSpecial = !password.matches("[A-Za-z0-9 ]*");
        boolean hasDigits = !password.matches("[0-9]");

        if (!(hasUppercaseAndLowerCase && hasSpecial && isBetween8And20Characters && hasDigits)){
            return false;
        }
        return true;
    }


    private void validatePhoneNumber(Account account, HashMap<String, String> errors) {
        if (account.getPhoneNumber().isEmpty()) {
            errors.put("phoneNumber", "Phone number should contain only digits and must be between 6-12 digits long");
        }

        if (!isPhoneNumberValid(account.getPhoneNumber())) {
            errors.put("phoneNumber", "Phone number should contain only digits and must be between 6-12 digits long");
        }
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber.matches("^\\d*$") && (phoneNumber.length() <= MAX_PHONE_NUMBER_DIGITS &&
                phoneNumber.length() >= MIN_PHONE_NUMBER_DIGITS);
    }
}