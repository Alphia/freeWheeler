package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.*;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.ReserveOrderService;
import com.trailblazers.freewheelers.service.TaxCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/shoppingCart")
public class ReserveController {

    private final ItemService itemService;
    private final AccountService accountService;
    private final ReserveOrderService reserveOrderService;
    private final TaxCalculatorService taxCalculatorService;

    private ShoppingCartItem shoppingItem;
    private AddressService addressService;

    @Autowired
    public ReserveController(ItemService itemService, AccountService accountService,
                             ReserveOrderService reserveOrderService,
                             TaxCalculatorService taxCalculatorService, AddressService addressService) {
        this.itemService = itemService;
        this.accountService = accountService;
        this.reserveOrderService = reserveOrderService;
        this.taxCalculatorService= taxCalculatorService;
        this.addressService = addressService;
    }

    @RequestMapping(method = RequestMethod.POST, params = "shoppingCart=Reserve Item")
    public String navigateToShoppingCart(Model model, Principal principal, @ModelAttribute Item item) {
        Account account = getAccount(principal);
        Item itemToReserve = itemService.get(item.getItemId());

        shoppingItem = setShoppingCartItem(account,itemToReserve,1);

        model.addAttribute("shoppingCartItem", shoppingItem);
        model.addAttribute("region", VatRegion.findRegion(account.getCountry_id()));
        return "shoppingCart";
    }

    private ShoppingCartItem setShoppingCartItem(Account account,Item itemToReserve,int quantity) {
        shoppingItem = new ShoppingCartItem();
        double tax = taxCalculatorService.calculateVat(account,itemToReserve).doubleValue();
        shoppingItem.setItem(itemToReserve);
        shoppingItem.setQuantity(quantity);
        shoppingItem.setTax(tax);
        return shoppingItem;
    }

    @RequestMapping(value = {"/confirm"}, method = RequestMethod.POST, params = "shoppingCart=Buy Item")
    public String navigateToConfirmationPage(Model model, Principal principal, @ModelAttribute ShoppingCartItem shoppingCartItem, @RequestParam("id") String id, @RequestParam("qqty") String qty) {

        Item itemToReserve = itemService.get(Long.parseLong(id));
        int quantity = Integer.parseInt(qty);
        Account account = getAccount(principal);
        shoppingItem = setShoppingCartItem(account,itemToReserve,quantity);
        model.addAttribute("shoppingCartItem", shoppingItem);
        model.addAttribute("region", VatRegion.findRegion(account.getCountry_id()));

        return "orderConfirmation";
    }

    @RequestMapping(value = {"/buy"}, method = RequestMethod.POST)
    public RedirectView addToReserveItem(Model model, Principal principal, HttpServletRequest request) {
        Account account = getAccount(principal);
        saveNewOrder(request, account);
        List<Item> items = getOrderList(account);
        model.addAttribute("userDetail", account);
        model.addAttribute("items", items);

        return new RedirectView("../userProfile");
    }

    private Account getAccount(Principal principal) {
        String userName = principal.getName();
        return accountService.getAccountByName(userName);
    }

    private void saveNewOrder(HttpServletRequest request, Account account) {
        String id = request.getParameter("id");
        Item item = itemService.get(Long.parseLong(id));
        ReserveOrder order = new ReserveOrder(account.getAccount_id(), item.getItemId(), new Date());

        int quantity = Integer.parseInt(request.getParameter("quantity"));
        order.setQuantity(quantity);

        reserveOrderService.save(order);
        itemService.decreaseQuantity(item, quantity);
    }

    private List<Item> getOrderList(Account account) {
        List<ReserveOrder> reserveOrders = reserveOrderService.findAllOrdersByAccountId(account.getAccount_id());
        List<Item> items = new ArrayList<Item>();
        for (ReserveOrder reserveOrder : reserveOrders) {
            items.add(itemService.get(reserveOrder.getItem_id()));
        }
        return items;
    }
}
