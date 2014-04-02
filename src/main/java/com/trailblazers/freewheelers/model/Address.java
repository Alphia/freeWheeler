package com.trailblazers.freewheelers.model;

/**
 * Created by twer on 4/1/14.
 */
public class Address {
    private String name;
    private String phone;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String country;
    private String postcode;


    public Address(String name, String phone, String street1, String street2, String city, String state, String country, String postcode) {

        this.name = name;
        this.phone = phone;
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postcode = postcode;
    }
}
