package cse305.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cse305.model.entities.Item;
import cse305.web.service.ItemService;

@Controller
@RequestMapping(value="/browse")
public class BrowseController {
	
	@RequestMapping("")
	public String browse(Model model) {
		ItemService itemService = new ItemService();
		List<Item> items = itemService.loadAllItems();
		model.addAttribute("items", items);
		return "browse";
	}
	
	
}
