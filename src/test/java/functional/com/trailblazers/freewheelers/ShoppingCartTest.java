package functional.com.trailblazers.freewheelers;

import org.junit.Test;

import static functional.com.trailblazers.freewheelers.helpers.SyntaxSugar.*;

public class ShoppingCartTest extends UserJourneyBase {
    @Test
    public void shouldNavigateToShoppingCartPageWhenReserveItem() throws Exception {
        String jan = "Jan Plewka";
        String itemName = "Scattante XRL Comp Road Bike";

        admin
                .there_is_no_account_for(jan);

        user
                .is_logged_out()
                .creates_an_account(jan, EMAIL, PASSWORD, PASSWORD, PHONE_NUMBER, COUNTRY)
                .logs_in_with(jan, PASSWORD)
                .visits_home_page()
                .reserves_item(itemName);

        screen
                .shows_shopping_cart();
    }
}
