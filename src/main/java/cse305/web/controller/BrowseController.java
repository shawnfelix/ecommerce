package cse305.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cse305.model.entities.Item;
import cse305.web.form.ItemDetailsForm;
import cse305.web.model.OrderModel;
import cse305.web.model.UserModel;
import cse305.web.service.ItemService;
import cse305.web.service.OrderService;

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
	
	@RequestMapping("/itemdetails/{productId}")
	public String itemDetails(@PathVariable(value="productId") String productId, Model model) {
		ItemService itemService = new ItemService();
		Item item = itemService.getItemDetails(Integer.valueOf(productId));
		model.addAttribute("item", item);
		return "itemdetails";
	}
	
	@RequestMapping("/itemdetails/{productId}/buy")
	public String itemDetailsBuy(@ModelAttribute ItemDetailsForm form, @PathVariable(value="productId") String productId,
			HttpServletRequest request, Model model) {
		OrderService orderService = new OrderService();
		UserModel userModel = (UserModel)request.getSession().getAttribute("usermodel");
		
		OrderModel orderModel = orderService.addItemToCart(userModel.getUserId(), Integer.valueOf(productId), form.getQuantity());

		model.addAttribute("ordermodel", orderModel);
		model.addAttribute("items", orderModel.getCartItems());
		return "cart";
	}
	
	@ModelAttribute("itemDetailsForm")
	public ItemDetailsForm getItemDetailsForm() {
		return new ItemDetailsForm();
	}
	
}
