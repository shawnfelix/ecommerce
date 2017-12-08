package cse305.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cse305.web.form.SignInForm;
import cse305.web.model.UserModel;
import cse305.web.service.UserService;

@Controller
@Scope("session")
@RequestMapping(value="/signin")
public class SignInController {

	@RequestMapping("")
	public String loadsignin(Model model) {
		
		model.addAttribute("signinForm", new SignInForm());
		return "signin";
	}
	
	
	@RequestMapping("/action/signout")
	public String signout(HttpServletRequest request) {
		request.getSession().setAttribute("usermodel", null);
		return "redirect:/signin";
	}
	
	@RequestMapping("/action/verify" )
	public String signin(@ModelAttribute SignInForm form,
			Model model, HttpServletRequest request) {
		UserService service = new UserService();
		UserModel userModel = service.signInAttempt(form.getUsername(), form.getPassword());
		
		if(userModel != null) {
			request.getSession().setAttribute("usermodel", userModel);
			return "redirect:/index";
		}
		return "redirect:/signin";
	}
	
	
	@ModelAttribute("signinForm")
	public SignInForm getSignInForm() {
		return new SignInForm();
	}
}
