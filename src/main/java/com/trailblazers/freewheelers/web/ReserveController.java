package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.ReserveOrder;
import com.trailblazers.freewheelers.service.impl.AccountService;
import com.trailblazers.freewheelers.service.impl.ItemService;
import com.trailblazers.freewheelers.service.impl.ReserveOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Date;

@Controller
@RequestMapping("/shoppingCart")
public class ReserveController {

    private ItemService itemService;
    private AccountService accountService;
    private ReserveOrderService reserveOrderService;

    @Autowired
    public ReserveController(ItemService itemService, AccountService accountService, ReserveOrderService reserveOrderService) {
        this.itemService = itemService;
        this.accountService = accountService;
        this.reserveOrderService = reserveOrderService;
    }

    @RequestMapping(method = RequestMethod.POST, params="shoppingCart=Reserve Item")
    public String navigateToShoppingCart(Model model, Principal principal, @ModelAttribute Item item){
        if (principal == null) {
            return "redirect:login";
        }

        String userName = principal.getName();
        Account account =  accountService.getAccountIdByName(userName);
        Item itemToReserve =  itemService.get(item.getItemId());
        Date rightNow = new Date();

        ReserveOrder reserveOrder = new ReserveOrder(account.getAccount_id(), itemToReserve.getItemId(), rightNow);

        reserveOrderService.save(reserveOrder);
        itemService.decreaseQuantityByOne(itemToReserve);

        model.addAttribute("item", itemToReserve);
        return "shoppingCart";
    }
}
