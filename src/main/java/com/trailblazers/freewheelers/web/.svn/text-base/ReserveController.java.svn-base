package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.ReserveOrder;
import com.trailblazers.freewheelers.model.ShoppingCartItem;
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


import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.util.Assert.isNull;

@Controller
@RequestMapping("/shoppingCart")
public class ReserveController {

    private ItemService itemService;
    private AccountService accountService;
    private ReserveOrderService reserveOrderService;
    ReserveOrder reserveOrder;
    ShoppingCartItem shoppingItem;
    TaxCalculatorService taxCalculatorService;

    @Autowired
    public ReserveController(ItemService itemService, AccountService accountService, ReserveOrderService reserveOrderService) {
        this.itemService = itemService;
        this.accountService = accountService;
        this.reserveOrderService = reserveOrderService;
        this.taxCalculatorService=new TaxCalculatorService();
    }

    @RequestMapping(method = RequestMethod.POST, params = "shoppingCart=Reserve Item")
    public String navigateToShoppingCart(Model model, Principal principal, @ModelAttribute Item item) {
        if (principal == null) {
            return "redirect:login";
        }
        String userName = principal.getName();
        Account account = accountService.getAccountIdByName(userName);
        Item itemToReserve = itemService.get(item.getItemId());

        shoppingItem = setShoppingCartItem(account,itemToReserve,1);


        model.addAttribute("shoppingCartItem", shoppingItem);
        return "shoppingCart";
    }

    private ShoppingCartItem setShoppingCartItem(Account account,Item itemToReserve,int quantity) {
        shoppingItem=new ShoppingCartItem();
        double tax=Double.parseDouble(taxCalculatorService.calculateVat(account,itemToReserve).toString());
        shoppingItem.setItem(itemToReserve);
        shoppingItem.setQuantity(quantity);
        shoppingItem.setTax(tax);
        return shoppingItem;
    }

    @RequestMapping(value = {"/confirm"}, method = RequestMethod.POST, params = "shoppingCart=Buy Item")
    public String navigateToConfirmationPage(Model model, Principal principal, @ModelAttribute ShoppingCartItem shoppingCartItem, @RequestParam("id") String id, @RequestParam("qqty") String qty) {

        Item itemToReserve = itemService.get(Long.parseLong(id));
        int quantity = Integer.parseInt(qty);
        String userName = principal.getName();
        Account account = accountService.getAccountIdByName(userName);
        shoppingItem = setShoppingCartItem(account,itemToReserve,quantity);
        model.addAttribute("shoppingCartItem", shoppingItem);

        return "orderConfirmation";
    }

    @RequestMapping(value = {"/buy"}, method = RequestMethod.POST, params = "shoppingCart=Buy")
    public String addToReserveItem(Model model, Principal principal, @ModelAttribute ShoppingCartItem shoppingCartItem, @RequestParam("id") String id, @RequestParam("qqty") String qty) {
        String userName = principal.getName();
        int quantity = Integer.parseInt(qty);
        Account account = accountService.getAccountIdByName(userName);
        Item itemToReserve = setReserveOrder(id, quantity, account);

        itemService.decreaseQuantity(itemToReserve, quantity);
        List<Item> items = getOrderList(account);
        model.addAttribute("userDetail", account);
        model.addAttribute("items", items);
        return "userProfile";
    }

    private Item setReserveOrder(String id, int qty, Account account) {
        Item itemToReserve = itemService.get(Long.parseLong(id));
        Date rightNow = new Date();
        reserveOrder=new ReserveOrder();
        reserveOrder.setQuantity(qty);
        ReserveOrder reserveOrder = new ReserveOrder(account.getAccount_id(), itemToReserve.getItemId(), rightNow);

        reserveOrderService.save(reserveOrder);
        return itemToReserve;
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
