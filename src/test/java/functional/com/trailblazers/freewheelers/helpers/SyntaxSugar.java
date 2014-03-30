package functional.com.trailblazers.freewheelers.helpers;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;

public class SyntaxSugar {

    public static final String PHONE_NUMBER = "555123456";
    public static final String INVALID_PHONE_NUMBER = "555&234*56";
    public static final String PASSWORD = "sECret12#";
    public static final String EMAIL = "somebody@something.de";
    public static final BigDecimal SOME_PRICE = valueOf(49.99);
    public static final String PASSWORD2 = "SecRet1@3";
    public static final String COUNTRY = "Germany";
    public static final String COUNTRY_NOT_SELECTED = "Select";
    public static final long COUNTRY_ID=1;

    public static final String EMPTY = "";
    public static final String NO_QUANTITY = "";
    public static final long ONLY_ONE_LEFT = 1L;
    public static final String REALLY_EXPENSIVE = "2899.00";
    public static final String SOME_DESCRIPTION = "4 x red, curved Arrow shape, screw fastening";
    public static final String A_LOT = "1000";
    public static final String INVALID_PASSWORD = "fdslfj23";

    public static String emailFor(String userName) {
        return userName.replace(' ', '-') + "@random-email.com";
    }

    public static String from(String s) {
        return s;
    }

    public static String to(String s) {
        return s;
    }
}