package cse305.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cse305.model.entities.Item;
import cse305.web.model.OrderModel;
import cse305.web.model.UserModel;
import cse305.web.service.ItemService;
import cse305.web.service.OrderService;

@Controller
public class HomeController {
	@RequestMapping("/home")
	public String home(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);

		return "index";
	}

	@RequestMapping("/index")
	public String index(HttpServletRequest request, Model model) {
		OrderService orderService = new OrderService();
		UserModel userModel = (UserModel) request.getSession().getAttribute("usermodel");
		if (userModel != null) {
			List<OrderModel> orders = orderService.getOrders(userModel.getUserId(), 10);
			model.addAttribute("orders", orders);
			
			ItemService itemService = new ItemService();
			List<Item> items = itemService.loadAllItems();
			items.subList(3, items.size()).clear();
			model.addAttribute("items", items);
		}

		return "index";
	}

	@RequestMapping("/createaccount")
	public String createaccount(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);

		return "createaccount";
	}

	@RequestMapping("/manageaccount")
	public String manageaccount(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);

		return "manageaccount";
	}

	/**
	 * For testing the app ONLY
	 */
	@RequestMapping("/greeting")
	public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
}
