package com.trailblazers.freewheelers.model;

public enum VatRegion {
    UK, EU_EXCEPT_UK, OUTSIDE_EU;

    public static VatRegion findRegion(long countryCode) {
        if (countryCode == Country.UK_ID) {
            return UK;
        } else if (countryCode == Country.FRANCE_ID || countryCode == Country.GERMANY_ID || countryCode == Country.ITALY_ID) {
            return EU_EXCEPT_UK;
        }
        return OUTSIDE_EU;
    }

    public static boolean isUk(long countryId) {
        return findRegion(countryId) == UK;
    }
}
