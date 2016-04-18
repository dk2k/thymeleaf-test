package ru.outofrange.controller;

import java.util.List;

import ru.outofrange.exc.UserEntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ru.outofrange.Helper;
import ru.outofrange.model.UserEntity;
import ru.outofrange.service.UserEntityService;

@Controller
public class UserController {
	
	@Autowired
    private UserEntityService userEntityService;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	
    @RequestMapping(value="/allusers", method=RequestMethod.GET)
    public ModelAndView userListPage() {
        ModelAndView mav = new ModelAndView("allusers");
        List<UserEntity> shopList = userEntityService.findAll();
        mav.addObject("users", shopList);
        return mav;
    }
	
    @RequestMapping(value="/create", method=RequestMethod.GET)
    public ModelAndView newUserPage() {
        ModelAndView mav = new ModelAndView("user-new", "user", new UserEntity());
        return mav;
    }
    
    @RequestMapping(value="/create", method=RequestMethod.POST)
    public ModelAndView createNewUser(@ModelAttribute UserEntity user, 
            final RedirectAttributes redirectAttributes) {
         
        ModelAndView mav = new ModelAndView();
        String message = "New user " + user.getName() + " was successfully created.";
        
        // encoding plain text password for security
        String plain = user.getPassword();
        user.setPassword(passwordEncoder.encode(plain));
        
        userEntityService.create(user);
        mav.setViewName("redirect:/index");
                 
        redirectAttributes.addFlashAttribute("message", message);   
        return mav;     
    }
    
    @RequestMapping(value="/user/edit/{id}", method=RequestMethod.GET)
    public ModelAndView editUserPage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit-user");
        UserEntity user = userEntityService.findById(id);
        modelAndView.addObject("user",user);
        return modelAndView;
    }
    
    @RequestMapping(value="/user/edit/{id}", method=RequestMethod.POST)
    public ModelAndView edditingTeam(@ModelAttribute UserEntity user, @PathVariable Long id) {
         
        ModelAndView modelAndView = new ModelAndView("index");
        
        //storing old password
        UserEntity oldUser = userEntityService.findById(id);
        String oldHashedPassword = oldUser.getPassword();
        user.setPassword(oldHashedPassword);
        
        String message = "User was successfully edited.";
        
        try {
			userEntityService.update(user);
		} catch (UserEntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "User with id = " + id + " not found.";
		}
        
        Helper.addAuthData(modelAndView);
        
        modelAndView.addObject("message", message);
         
        return modelAndView;
    }
    
    @RequestMapping(value="/user/delete/{id}", method=RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("index");
        
        String message = "User was successfully deleted.";
        try {
			userEntityService.delete(id);
		} catch (UserEntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "User with id = " + id + " not found.";
		}
        
        Helper.addAuthData(modelAndView);
        
        modelAndView.addObject("message", message);
        return modelAndView;
    }
}
