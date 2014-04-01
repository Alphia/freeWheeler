package functional.com.trailblazers.freewheelers;

import org.junit.Test;

import static functional.com.trailblazers.freewheelers.helpers.SyntaxSugar.ONLY_ONE_LEFT;
import static functional.com.trailblazers.freewheelers.helpers.SyntaxSugar.PASSWORD;


public class OrderConfirmTest extends UserJourneyBase {
    @Test
    public void shouldNavigateToConfirmPage() throws Exception {
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
                .shows_order_confirmation(Simplon_Frame);
        user
                .fill_delivery_address()
                .confirm_shopping_cart();
        screen
                .shows_completed_order_message()
                .go_to_user_profile();

    }
}
