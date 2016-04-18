package ru.outofrange;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

public class Helper {
    
    public static void addAuthData(ModelAndView mav){
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        String name = auth.getName();
	        if (!"anonymousUser".equals(name)){
	        	mav.addObject("logged_user", name);
	        }
	    }
    }
}
