package com.trailblazers.freewheelers.service;

import com.trailblazers.freewheelers.model.Country;

public enum VatRegion {
    UK, EU_EXCEPT_UK, OUTSIDE_EU;

    public static VatRegion findRegion(long countryCode) {
        if (countryCode == Country.UK_ID) {
            return UK;
        } else if (countryCode == Country.USA_ID || countryCode == Country.CANADA_ID){
            return OUTSIDE_EU;
        }
        return EU_EXCEPT_UK;
    }

    public static boolean isUk(long countryId) {
        return findRegion(countryId) == UK;
    }
}
