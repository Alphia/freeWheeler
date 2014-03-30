package com.trailblazers.freewheelers.model;

import com.trailblazers.freewheelers.service.CountryService;

public class Account {

    private CountryService countryService;
    private Long account_id;
    private String account_name;
    private String password;
    private boolean enabled;
    private String emailAddress;
    private String phoneNumber;
    private long country_id;
    private String confirmPassword;

    public Account(String account_name, String password, boolean enabled, String emailAddress, String phoneNumber, long country_id, String confirmPassword) {
        this.confirmPassword = confirmPassword;
        this.account_id = 0L;
        this.account_name = account_name;
        this.password = password;
        this.enabled = enabled;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.country_id = country_id;
    }

    public Account(CountryService countryService) {
        this.countryService = countryService;

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (enabled != account.enabled) return false;
        if (account_id != null ? !account_id.equals(account.account_id) : account.account_id != null) return false;
        if (!account_name.equals(account.account_name)) return false;
        if (country_id != account.country_id) return false;
        if (!emailAddress.equals(account.emailAddress)) return false;
        if (!password.equals(account.password)) return false;
        if (!phoneNumber.equals(account.phoneNumber)) return false;

        return true;
    }

    public Account() {
        this.account_id = 0L;
        this.countryService = new CountryService();
    }

    public Long getAccount_id() {
        return account_id;
    }

    public String getAccount_name() {
        return account_name;
    }

    public Account setAccount_name(String account_name) {
        this.account_name = account_name;
        return this;
    }

    public Account setAccount_id(Long account_id) {
        this.account_id = account_id;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Account setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Account setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public Account setEmail_address(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public String getEmail_address() {
        return emailAddress;
    }

    public Account setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Account setCountry_id(long country_id) {
        this.country_id =country_id;
        return this;
    }

    public long getCountry_id() {
        return country_id;
    }

    public Country getCountry() {

        return countryService.get(this.country_id);
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public Account setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
