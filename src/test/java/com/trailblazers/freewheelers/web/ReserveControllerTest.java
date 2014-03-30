package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.ReserveOrder;
import com.trailblazers.freewheelers.service.impl.AccountService;
import com.trailblazers.freewheelers.service.impl.ItemService;
import com.trailblazers.freewheelers.service.impl.ReserveOrderService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;

import java.security.Principal;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ReserveControllerTest {

    private ExtendedModelMap mockModel;
    private Item item;
    private ItemService mockItemService;
    private AccountService mockAccountService;
    private ReserveOrderService reserveOrderService;
    private ReserveOrder reserveOrder;
    private ReserveController reserveController;
    private Account account;

    @Before
    public void setUp() throws Exception {
        this.mockModel = mock(ExtendedModelMap.class);
        this.mockItemService = mock(ItemService.class);
        this.mockAccountService = mock(AccountService.class);
        this.reserveOrderService = mock(ReserveOrderService.class);

        account = new Account();
        account.setAccount_id(1L);
        this.item = new Item();
        item.setItemId(1L);
        this.reserveOrder = new ReserveOrder(account.getAccount_id(), item.getItemId(), new Date());
        this.reserveController = new ReserveController(mockItemService, mockAccountService, reserveOrderService);
    }

    @Test
    public void shouldReturnShoppingCartWhenNavigateToShoppingCart() throws Exception {
        Principal mockPrincipal = mock(Principal.class);
        given(mockPrincipal.getName()).willReturn("Jan");
        given(mockAccountService.getAccountIdByName("Jan")).willReturn(account);
        given(mockItemService.get(item.getItemId())).willReturn(item);

        String result = reserveController.navigateToShoppingCart(mockModel, mockPrincipal, item);

        verify(reserveOrderService).save(reserveOrder);
        verify(mockItemService).decreaseQuantityByOne(item);
        assertThat(result, is("shoppingCart"));
    }

    @Test
    public void shouldRedirectToLoginPageWhenPrincipleIsNull() throws Exception {
        String result = reserveController.navigateToShoppingCart(mockModel, null, item);
        assertThat(result, is("redirect:login"));
    }
}
