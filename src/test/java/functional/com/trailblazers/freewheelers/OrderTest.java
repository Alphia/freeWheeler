package functional.com.trailblazers.freewheelers;

import functional.com.trailblazers.freewheelers.helpers.URLs;
import org.junit.Test;

import static functional.com.trailblazers.freewheelers.helpers.SyntaxSugar.ONLY_ONE_LEFT;
import static functional.com.trailblazers.freewheelers.helpers.SyntaxSugar.PASSWORD;

public class OrderTest extends UserJourneyBase {

    @Test
    public void testOrderProcess() throws Exception {
        String Arno = "Arno Admin";
        String Bob = "Bob Buyer";
        String Simplon_Frame = "Simplon Pavo 3 Ultra";

        admin
                .there_is_an_admin(Arno, PASSWORD)
                .there_is_a_user(Bob, PASSWORD)
                .there_is_a_frame(Simplon_Frame, ONLY_ONE_LEFT);

        user
                .logs_in_with(Bob, PASSWORD)
                .visits_home_page();

        screen
                .should_list_item(Simplon_Frame);


        user
                .addsToshoppingCart(Simplon_Frame)
                .checksOut()
                .Buys()
                .visits_home_page();

        screen
                .should_not_list_item(Simplon_Frame);

        user
                .logs_in_with(Arno, PASSWORD)
                .visits_admin_profile();

        screen
                .there_should_be_an_order(Simplon_Frame, "NEW");

        user
                .changes_order_status(Simplon_Frame, "IN_PROGRESS");

        screen
                .there_should_be_an_order(Simplon_Frame, "IN_PROGRESS");

        user.is_logged_out();
    }

    @Test
    public void shouldNavigateToLoginPageWhenReserveItemWithoutLogin() throws Exception {
        String itemName = "Simplon Pavo 3 Ultra";
        String Bob = "Bob Buyer";

        admin.create_item(itemName, 1L)
                .there_is_a_user(Bob, PASSWORD);

        user.visits_home_page()
                .reserves_item(itemName);

        screen.shows_login();

        user.logs_in_with(Bob, PASSWORD);

        screen.shows_correct_url(URLs.shoppingCart());
    }
}
