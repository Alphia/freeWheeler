package com.trailblazers.freewheelers.service.validation;

import com.trailblazers.freewheelers.model.Account;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class PasswordValidator {

    public static final int MIN_PASSWORD_SIZE = 8;
    public static final int MAX_PASSWORD_SIZE = 20;

    public void validatePassword(Account account, HashMap<String, String> errors) {
        if (account.getPassword().isEmpty()) {
            errors.put("password", "Password can't be empty");
        }
        else if(!hasNecessaryCharacters(account.getPassword())) {
            errors.put("validPassword", "Must be between 8-20 characters, and contain at least 1 number, 1 lowercase letter, 1 uppercase letter, 1 special character!");
        }

        if(!account.getConfirmPassword().equals(account.getPassword())){
            errors.put("confirmPassword", "Confirm password must match password!");
        }
    }

    private boolean hasNecessaryCharacters(String password) {
        boolean hasUppercaseAndLowerCase = !password.equals(password.toLowerCase()) && !password.equals(password.toUpperCase());
        boolean isBetween8And20Characters = password.length() >= MIN_PASSWORD_SIZE && password.length() <= MAX_PASSWORD_SIZE;
        boolean hasSpecial = !password.matches("[A-Za-z0-9 ]*");
        boolean hasDigits = password.matches(".*\\d+.*");

        if (!(hasUppercaseAndLowerCase && hasSpecial && isBetween8And20Characters && hasDigits)){
            return false;
        }
        return true;
    }
}
