package cse305.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cse305.model.entities.CartItem;
import cse305.web.model.OrderModel;
import cse305.web.model.UserModel;
import cse305.web.service.OrderService;

@Controller
public class CartController {

	@RequestMapping("/cart")
	public String loadCart(HttpServletRequest request, Model model) {
		UserModel userModel = (UserModel)request.getSession().getAttribute("usermodel");
		
		OrderService orderService = new OrderService();
		OrderModel orderModel = orderService.getActiveOrder(userModel.getUserId());
		
		model.addAttribute("items", orderModel.getCartItems());
		model.addAttribute("ordermodel", orderModel);
		return "cart";
	}
	
	@RequestMapping("/cart/delete/{productId}")
	public String deleteFromCart(@PathVariable(value="productId") String productId,
			HttpServletRequest request, Model model) {
		OrderService orderService = new OrderService();
		UserModel userModel = (UserModel)request.getSession().getAttribute("usermodel");
		
		OrderModel orderModel= orderService.removeItemFromCart(userModel.getUserId(), Integer.valueOf(productId));
		
		model.addAttribute("items", orderModel.getCartItems());
		model.addAttribute("ordermodel", orderModel);
		
		return "cart";
	}
	
	
	@RequestMapping("/cart/checkout")
	public String checkout(HttpServletRequest request, Model model) {
		OrderService orderService = new OrderService();
		
		
		orderService.checkout();
		
		//TODO
		//return "orders";
		return "cart";
	}
	
}
