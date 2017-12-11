package cse305.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cse305.model.entities.Item;
import cse305.web.form.AddItemForm;
import cse305.web.model.UserModel;
import cse305.web.service.ItemService;

@Controller
public class EmployeeController {

	@RequestMapping("/employee")
	public String employee(HttpServletRequest request, Model model) {
		UserModel userModel = (UserModel)request.getSession().getAttribute("usermodel");
		
		model.addAttribute("additemform", new AddItemForm());
		model.addAttribute("employeeModel", userModel);
		return "employee";
	}
	
	@RequestMapping("/employee/submit")
	public String addItemSubmit(@ModelAttribute AddItemForm form, HttpServletRequest request, Model model) {
		UserModel userModel = (UserModel)request.getSession().getAttribute("usermodel");
		
		Item item = new Item();
		item.setName(form.getName());
		item.setPrice(Double.valueOf(form.getPrice()));
		
		ItemService service = new ItemService();
		service.addNewItem(item, userModel.getUserId());
		
		return "redirect:/employeebrowse";
	}
	
	@RequestMapping("/employeebrowse/delete/{productId}")
	public String removeItem(@PathVariable String productId, HttpServletRequest request, Model model) {
		UserModel userModel = (UserModel)request.getSession().getAttribute("usermodel");
		
		ItemService service = new ItemService();
		service.removeItem(Integer.valueOf(productId));
		
		return "redirect:/employeebrowse";
	}
	
	@RequestMapping("/employeebrowse")
	public String employeeBrowse(HttpServletRequest request, Model model) {
		ItemService itemService = new ItemService();
		List<Item> items = itemService.loadAllItems();
		model.addAttribute("items", items);
		return "employeebrowse";
	}
}
