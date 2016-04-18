package ru.outofrange.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ru.outofrange.Helper;
 
@Controller
public class IndexController {
 
    @RequestMapping(value={"/", "/index"})
    ModelAndView index() {
    	
    	ModelAndView mav = new ModelAndView("index");
    	
    	Helper.addAuthData(mav); 
        return mav; 
    }
}
