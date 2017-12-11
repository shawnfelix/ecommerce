package cse305.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cse305.web.model.OrderModel;
import cse305.web.model.UserModel;
import cse305.web.service.OrderService;

@Controller
public class AccountController {
	
	@RequestMapping("/orders")
	public String orders(HttpServletRequest request, Model model) {
		OrderService orderService = new OrderService();
		
		
		UserModel user = (UserModel)request.getSession().getAttribute("usermodel");
		List<OrderModel> orders = orderService.getOrders(user.getUserId(), 20);
		
		model.addAttribute("orders",orders);
		
		return "orders";
	}
	
}
