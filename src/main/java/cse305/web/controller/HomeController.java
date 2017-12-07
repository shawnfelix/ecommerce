package cse305.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cse305.model.dao.ItemDao;

@Controller
public class HomeController {
    @RequestMapping("/home")
    public String home(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        
        
        
        return "index";
    }
    
   
    
    
    
    
    
    
    
    
    /**
     * For testing the app ONLY
     */
    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
}
