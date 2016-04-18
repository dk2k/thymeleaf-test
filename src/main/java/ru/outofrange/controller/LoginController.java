package ru.outofrange.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ru.outofrange.LoginModel;

@Controller
@RequestMapping("login")
public class LoginController {
 
    @RequestMapping(method = RequestMethod.GET)
    public String init(ModelMap modelMap) {
        return "login";
    }
 
    @RequestMapping(method = RequestMethod.POST)
    public String submit(ModelMap modelMap, @ModelAttribute("loginModel") @Valid LoginModel loginModel,
    		final RedirectAttributes redirectAttributes) {
    	String message = "You've successfully logged in";
	    redirectAttributes.addFlashAttribute("message", message); 
	    return "redirect:/index";
    }
}
