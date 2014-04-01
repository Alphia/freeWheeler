package com.trailblazers.freewheelers.service.validation;

import com.trailblazers.freewheelers.mappers.AccountMapper;
import com.trailblazers.freewheelers.mappers.MyBatisUtil;
import com.trailblazers.freewheelers.model.Account;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class AccountValidation {

    private static final int MAX_PHONE_NUMBER_DIGITS = 12;
    private static final int MIN_PHONE_NUMBER_DIGITS = 6;

    private final AccountMapper accountMapper;
    private final SqlSession sqlSession;
    private final PasswordValidator passwordValidator;

    @Autowired
    public AccountValidation(PasswordValidator passwordValidator) {
        this(MyBatisUtil.getSqlSessionFactory().openSession(), passwordValidator);
    }

    public AccountValidation(SqlSession sqlSession, PasswordValidator passwordValidator) {
        this.sqlSession= sqlSession;
        this.accountMapper = sqlSession.getMapper(AccountMapper.class);
        this.passwordValidator = passwordValidator;
    }

    public HashMap<String, String> verifyInputs(Account account) {
        HashMap<String, String> errors = new HashMap<String, String>();

        validateEmail(account, errors);
        passwordValidator.validatePassword(account, errors);
        validateName(account, errors);
        validatePhoneNumber(account, errors);
        validateCountry(account, errors);

        return errors;
    }

    private void validateEmail(Account account, HashMap<String, String> errors) {
        if (!account.getEmail_address().contains("@")) {
            errors.put("email", "Must enter a valid email!");
        }
        else if (accountMapper.getByEmail(account.getEmail_address()) != null){
            errors.put("email", "Email is already in use. Please enter a different email address.");
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