package functional.com.trailblazers.freewheelers;

import functional.com.trailblazers.freewheelers.helpers.URLs;
import org.junit.Ignore;
import org.junit.Test;

import static functional.com.trailblazers.freewheelers.helpers.SyntaxSugar.*;

public class ShoppingCartTest extends UserJourneyBase {

    @Test
    public void shouldNavigateToShoppingCartPageWhenReserveItemWithUkUser() throws Exception {
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
                .addsToshoppingCart(itemName);

        screen
                .shows_correct_url(URLs.shoppingCart())
                .show_vat_percentage_label()
                .show_vat_tax(vatTax);
        user
                .checksOut()
                .Buys()
                .visits_home_page();
        screen
                .should_list_item_at_homepage(itemName);
    }

    @Test
    public void shouldShowVatTaxExemptForGermanUser() throws Exception {
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
                .addsToshoppingCart(itemName);

        screen
                .shows_correct_url(URLs.shoppingCart())
                .show_vat_exempt()
                .show_vat_tax(vatTax);
    }

    @Test
    public void shouldShowVatTaxExemptForUsaUser() throws Exception {
        String john = "John Smith";
        String itemName = "Scattante XRL Comp Road Bike";

        admin
                .there_is_no_account_for(john);

        user
                .is_logged_out()
                .creates_an_account(john, AMERICAN_EMAIL, PASSWORD, PASSWORD, PHONE_NUMBER, COUNTRY_USA)
                .logs_in_with(john, PASSWORD)
                .visits_home_page()
                .addsToshoppingCart(itemName);

        screen
                .shows_correct_url(URLs.shoppingCart())
                .shows_no_vat();
    }

}
