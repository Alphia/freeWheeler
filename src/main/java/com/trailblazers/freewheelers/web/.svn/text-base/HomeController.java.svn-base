package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {

    ItemService itemService = new ItemService();

	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model, @ModelAttribute("item") Item item) {
        model.addAttribute("items", itemService.getItemsWithNonZeroQuantity());
        return "home";
	}
}

