package cse305.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cse305.web.form.ManageAccountForm;
import cse305.web.form.SignInForm;
import cse305.web.model.OrderModel;
import cse305.web.model.UserModel;
import cse305.web.service.OrderService;
import cse305.web.service.UserService;

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
	
	@RequestMapping("/manageaccount")
	public String manageaccount(@ModelAttribute ManageAccountForm form, HttpServletRequest request, Model model) {
		UserModel user = (UserModel)request.getSession().getAttribute("usermodel");
		
		
		model.addAttribute("manageaccountform", generateManageAccountForm(user));
		return "manageaccount";
	}
	
	@RequestMapping("/manageaccount/submit")
	public String manageaccountSubmit(@ModelAttribute ManageAccountForm form, HttpServletRequest request, Model model) {
		UserModel user = (UserModel)request.getSession().getAttribute("usermodel");
		
		UserService userService = new UserService();
		
		UserModel newModel = userService.updateUserModel(form, user.getUserId());
		
		
		model.addAttribute("manageaccountform", generateManageAccountForm(newModel));
		request.getSession().setAttribute("usermodel", newModel);
		return "manageaccountconfirm";
	}
	
	private ManageAccountForm generateManageAccountForm(UserModel model) {
		ManageAccountForm form = new ManageAccountForm();
		
		form.setAddress(model.getMailAddress());
		form.setEmailAddress(model.getEmail());
		form.setFirstName(model.getFirstName());
		form.setLastName(model.getLastName());
		form.setPassword(model.getPassword());
		form.setPhoneNumber(model.getPhone());
		
		return form;
		
	}
}
