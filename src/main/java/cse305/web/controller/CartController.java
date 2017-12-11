package cse305.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cse305.model.entities.Payment;
import cse305.model.entities.Shipment;
import cse305.web.form.PaymentForm;
import cse305.web.model.OrderModel;
import cse305.web.model.UserModel;
import cse305.web.service.OrderService;

@Controller
public class CartController {

	@RequestMapping("/cart")
	public String loadCart(HttpServletRequest request, Model model) {
		UserModel userModel = (UserModel) request.getSession().getAttribute("usermodel");

		OrderService orderService = new OrderService();
		OrderModel orderModel = orderService.getActiveOrder(userModel.getUserId());

		model.addAttribute("items", orderModel.getCartItems());
		model.addAttribute("ordermodel", orderModel);
		return "cart";
	}

	@RequestMapping("/cart/delete/{productId}")
	public String deleteFromCart(@PathVariable(value = "productId") String productId, HttpServletRequest request,
			Model model) {
		OrderService orderService = new OrderService();
		UserModel userModel = (UserModel) request.getSession().getAttribute("usermodel");

		OrderModel orderModel = orderService.removeItemFromCart(userModel.getUserId(), Integer.valueOf(productId));

		model.addAttribute("items", orderModel.getCartItems());
		model.addAttribute("ordermodel", orderModel);

		return "cart";
	}

	@RequestMapping("/payment")
	public String getPaymentInfo(HttpServletRequest request, Model model) {
		UserModel userModel = (UserModel) request.getSession().getAttribute("usermodel");
		OrderService orderService = new OrderService();

		OrderModel orderModel = orderService.getActiveOrder(userModel.getUserId());
		
		model.addAttribute("paymentform", new PaymentForm());
		model.addAttribute("ordermodel", orderModel);
		request.getSession().setAttribute("ordermodel", orderModel);
		
		
		return "payments";
	}
	
	@RequestMapping("/payments/checkout")
	public String checkout(@ModelAttribute PaymentForm form, 
			HttpServletRequest request, Model model) {
		OrderModel orderModel = (OrderModel)request.getSession().getAttribute("ordermodel");

		Payment payment = new Payment();
		payment.setCardExp(form.getCardExp());
		payment.setCardNumber(form.getCardNumber());
		payment.setOrderId(orderModel.getOrderId());
		payment.setType(form.getType());
		
		Shipment shipment = new Shipment();
		shipment.setMailingAddress(form.getAddress());
		shipment.setOrderId(orderModel.getOrderId());
		shipment.setType("5.95 One-Day Shipping");
		shipment.setCost(5.95);
		shipment.setDetails("Estimated arrival:");
		
		orderModel.setShipment(shipment);
		orderModel.setPayment(payment);
		
		OrderService orderService = new OrderService();
		orderService.checkout(orderModel);
		
		return "ordersuccess";
	}

}
