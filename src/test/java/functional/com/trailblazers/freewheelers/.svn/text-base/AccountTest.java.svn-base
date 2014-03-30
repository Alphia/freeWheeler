package functional.com.trailblazers.freewheelers;

import org.junit.Test;

import static functional.com.trailblazers.freewheelers.helpers.SyntaxSugar.*;

public class AccountTest extends UserJourneyBase {

    @Test
    public void testCreateAccount() throws Exception {
        String jan = "Jan Plewka";

        admin
                .there_is_no_account_for(jan);

        user
                .is_logged_out()
                .logs_in_with(jan, PASSWORD);

        screen
                .shows_error_alert("login attempt was not successful");

        user
                .creates_an_account(jan, EMAIL, PASSWORD, PASSWORD, EMPTY, COUNTRY);

        screen
                .shows_error_alert("There were errors");

        user
                .creates_an_account(jan, EMAIL, PASSWORD, PASSWORD, PHONE_NUMBER, COUNTRY);

        screen
                .shows_message("account has been created");

        screen
                .shows_in_navbar("Welcome " + jan);
    }

    @Test
    public void shouldNotCreateAccountWhenPasswordIsEmpty() throws Exception {
        String jan = "Jan Plewka";

        admin
                .there_is_no_account_for(jan);

        user
                .creates_an_account(jan, EMAIL, EMPTY, EMPTY, PHONE_NUMBER, COUNTRY);
        screen
                .shows_error_alert("There were errors");
    }

    @Test
    public void shouldNotCreateAccountWhenPasswordIsInvalid() throws Exception {
        String jan = "Jan Plewka";

        admin
                .there_is_no_account_for(jan);

        user
                .creates_an_account(jan, EMAIL, INVALID_PASSWORD, INVALID_PASSWORD, PHONE_NUMBER, COUNTRY);
        screen
                .shows_error_alert("There were errors");
    }

    @Test
    public void shouldNotCreateAccountWhenConfirmPasswordIsDoesNotMatchPassword() throws Exception {
        String jan = "Jan Plewka";

        admin
                .there_is_no_account_for(jan);
        user
                .creates_an_account(jan, EMAIL, PASSWORD, PASSWORD2, PHONE_NUMBER, COUNTRY);
        screen
                .shows_error_alert("There were errors");
    }

    @Test
    public void shouldNotCreateAccountWhenPhoneNumberIsInvalid() throws Exception {

        String jan = "Jan Plewka";

        admin
                .there_is_no_account_for(jan);

        user
                .creates_an_account(jan, EMAIL, PASSWORD, INVALID_PHONE_NUMBER, COUNTRY, COUNTRY);
        screen
                .shows_error_alert("There were errors");
    }

    @Test
    public void shouldNotCreateAccountWhenCountryNotSelected() throws Exception {

        String jan = "Jan Plewka";

        admin
                .there_is_no_account_for(jan);

        user
                .is_logged_out()
                .logs_in_with(jan, PASSWORD);

        screen
                .shows_error_alert("login attempt was not successful");

        user
                .creates_an_account(jan, EMAIL, PASSWORD, EMPTY, COUNTRY, COUNTRY);

        screen
                .shows_error_alert("There were errors");

        user
                .creates_an_account(jan, EMAIL, PASSWORD, PHONE_NUMBER, COUNTRY_NOT_SELECTED, COUNTRY);
        screen
                .shows_error_alert("There were errors");
    }

    @Test
    public void testAccessRights() throws Exception {
        String Hugo = "Hugo Huser";
        String Arno = "Arno Admin";

        admin
                .there_is_a_user(Hugo, PASSWORD)
                .there_is_an_admin(Arno, PASSWORD);

        user
                .is_logged_out()
                .visits_his_profile();
        screen
                .shows_login();

        user
                .logs_in_with(Hugo, PASSWORD)
                .visits_his_profile();
        screen
                .shows_profile_for(Hugo);

        user
                .visits_admin_profile();
        screen
                .shows_error_alert("access is denied");

        user
                .logs_in_with(Arno, PASSWORD)
                .visits_admin_profile();
        screen
                .shows_admin_profile();

        user
                .visits_profile_for(Hugo);
        screen
                .shows_profile_for(Hugo);
    }

    @Test
    public void shouldNotGoToAnotherUsersProfileFromOneUser() throws Exception {
        String Hugo = "Hugo Huser";
        String John = "John Adam";

        admin
                .there_is_a_user(Hugo, PASSWORD)
                .there_is_a_user(John, PASSWORD);

        user
                .logs_in_with(Hugo, PASSWORD)
                .visits_profile_for_another_user(John);
        screen
                .should_show_access_denied();
    }
}
