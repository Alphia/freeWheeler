package functional.com.trailblazers.freewheelers;

import org.junit.Test;

import static functional.com.trailblazers.freewheelers.helpers.SyntaxSugar.*;

public class OrderConfirmationTest extends UserJourneyBase {

    @Test
    public void shouldGoToUserProfileWhenConfirm() throws Exception {
        String Arno = "Arno Admin";
        String Bob = "Bob Buyer";
        String Simplon_Frame = "Simplon Pavo 3 Ultra";

        admin
                .there_is_an_admin(Arno, PASSWORD)
                .there_is_a_user(Bob, PASSWORD)
                .there_is_a_frame(Simplon_Frame, ONLY_ONE_LEFT);

        user
                .logs_in_with(Bob, PASSWORD)
                .visits_home_page()
                .addsToshoppingCart(Simplon_Frame)
                .checksOut();
        screen
                .shows_order_confirmation();
        user
                .fill_delivery_address()
                .confirm_shopping_cart();
        screen
                .go_to_user_profile();
    }

    @Test
    public void shouldTaxForUkUser() throws Exception {
        String jan = "Jan Plewka";
        String itemName = "Scattante XRL Comp Road Bike";
        double vatTax = 58.00;

        admin
                .there_is_no_account_for(jan);

        user
                .is_logged_out()
                .creates_an_account(jan, EMAIL, PASSWORD, PASSWORD, PHONE_NUMBER, COUNTRY_UK)
                .logs_in_with(jan, PASSWORD)
                .visits_home_page()
                .addsToshoppingCart(itemName)
                .checksOut();
        screen
                .show_vat_percentage_label()
                .show_vat_tax(vatTax);
    }

    @Test
    public void shouldNotTaxForGermanUser() throws Exception {
        String franz = "Franz Beckenbauer";
        String itemName = "Scattante XRL Comp Road Bike";
        double vatTax = 0;

        admin
                .there_is_no_account_for(franz);

        user
                .is_logged_out()
                .creates_an_account(franz, GERMAN_EMAIL, PASSWORD, PASSWORD, PHONE_NUMBER, COUNTRY)
                .logs_in_with(franz, PASSWORD)
                .visits_home_page()
                .addsToshoppingCart(itemName)
                .checksOut();
        screen
                .show_vat_exempt()
                .show_vat_tax(vatTax);
    }
}
