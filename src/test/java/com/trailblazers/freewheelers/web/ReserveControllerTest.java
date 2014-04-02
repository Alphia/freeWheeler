package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Address;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.ShoppingCartItem;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.ReserveOrderService;
import com.trailblazers.freewheelers.service.TaxCalculatorService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ReserveControllerTest {

    private ExtendedModelMap mockModel;
    private Item item;
    private ItemService mockItemService;
    private AccountService mockAccountService;
    private ReserveOrderService reserveOrderService;
    private ReserveController reserveController;
    private TaxCalculatorService taxCalculatorService;
    private ShoppingCartItem shoppingItem;
    private Account account;
    private Principal mockPrincipal;
    private AddressService addressService;

    @Before
    public void setUp() throws Exception {
        this.mockModel = mock(ExtendedModelMap.class);

        account = new Account();
        account.setAccount_id(1L);

        this.item = new Item();
        item.setItemId(1L);

        this.mockPrincipal = getPrincipal();
        this.addressService = mock(AddressService.class);
        this.mockItemService = mock(ItemService.class);
        this.mockAccountService = mock(AccountService.class);
        this.reserveOrderService = mock(ReserveOrderService.class);
        this.taxCalculatorService = new TaxCalculatorService();
        this.shoppingItem = new ShoppingCartItem();
        this.reserveController = new ReserveController(mockItemService, mockAccountService, reserveOrderService, taxCalculatorService, addressService);

        mocks();
    }
    @Test
    public void shouldNavigateToShoppingCartWithItem() throws Exception {

        shoppingItem.setItem(item);
        shoppingItem.setQuantity(1);

        String result = reserveController.navigateToShoppingCart(mockModel, mockPrincipal, item);
        assertThat(result, is("shoppingCart"));
        verify(mockModel).addAttribute("shoppingCartItem",shoppingItem);
    }

    private void mocks() {
        given(mockItemService.get(item.getItemId())).willReturn(item);
        given(mockAccountService.getAccountByName("Jan")).willReturn(account);
    }

    private Principal getPrincipal() {
        Principal mockPrincipal = mock(Principal.class);
        given(mockPrincipal.getName()).willReturn("Jan");
        return mockPrincipal;
    }

    @Test
    public void shouldNavigateToConfirmPageWithItem() throws Exception {
        String result = reserveController.navigateToConfirmationPage(mockModel, mockPrincipal, shoppingItem, "1", "2");
        assertThat(result, is("orderConfirmation"));
        shoppingItem.setItem(item);
        shoppingItem.setQuantity(2);
        verify(mockModel).addAttribute("shoppingCartItem",shoppingItem);
    }

    @Test
    public void shouldNavigateToUserProfile() throws Exception {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        given(mockRequest.getParameter("id")).willReturn("1");
        given(mockRequest.getParameter("quantity")).willReturn("2");

        RedirectView result = reserveController.addToReserveItem(mockModel, mockPrincipal, mockRequest);
        assertThat(result.getUrl(), is("../userProfile"));
    }

    @Test
    public void shouldDeductStockWhenConfirm() throws Exception {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        given(mockRequest.getParameter("id")).willReturn("1");
        given(mockRequest.getParameter("quantity")).willReturn("2");
        reserveController.addToReserveItem(mockModel, mockPrincipal, mockRequest);
        verify(mockItemService).decreaseQuantity(item,2);
    }

    @Test
    public void shouldSaveDeliveryAddressWhenConfirm() throws Exception {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        given(mockRequest.getParameter("id")).willReturn("1");
        given(mockRequest.getParameter("quantity")).willReturn("2");
        given(mockRequest.getParameter("name")).willReturn("Jan");
        given(mockRequest.getParameter("phone")).willReturn("123456789");
        given(mockRequest.getParameter("street1")).willReturn("Lane 5");
        given(mockRequest.getParameter("street2")).willReturn("Koregeon Park");
        given(mockRequest.getParameter("city")).willReturn("Pune");
        given(mockRequest.getParameter("state")).willReturn("Maharashtra");
        given(mockRequest.getParameter("country")).willReturn("India");
        given(mockRequest.getParameter("postcode")).willReturn("123456");

        reserveController.addToReserveItem(mockModel, mockPrincipal, mockRequest);
        AddressService mockAddressService = mock(AddressService.class);
        Address address = new Address("name","phone","street1","street2","city","state","country","postcode");
        verify(mockAddressService).save(address);
    }
}
