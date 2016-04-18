package ru.outofrange.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonView;

import ru.outofrange.jsonview.Views;
import ru.outofrange.model.Gender;
import ru.outofrange.model.UserEntity;
import ru.outofrange.service.UserEntityService;
import ru.outofrange.web.model.AjaxResponseBody;
import ru.outofrange.web.model.SearchCriteria;

@Controller
public class SearchController {
	
	@Autowired
    private UserEntityService userEntityService;
	
    @RequestMapping(value="/search", method=RequestMethod.GET)
    public ModelAndView userListPage() {
        ModelAndView mav = new ModelAndView("search");
        return mav;
    }
    
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/api/getSearchResult", 
			method={RequestMethod.POST})
	public @ResponseBody AjaxResponseBody getSearchResultViaAjax(@RequestBody final SearchCriteria search) {
	
		AjaxResponseBody result = new AjaxResponseBody();
		
		List<UserEntity> users  = searchForUsers(
				search.getUsername(),
				search.getEmail(),
				search.getName(),
				search.getLowerAge(),
				search.getUpperAge(),
				search.getLowerDate(),
				search.getUpperDate(),
				search.getGender());
		
		if (users.size() > 0) {
			result.setCode("200");
			result.setMsg("ok");
			result.setResult(users);
		} else {
			result.setCode("204");
			result.setMsg("No users found!");
		}
		
		return result;
	}

	
	private List<UserEntity> searchForUsers(
			String username,
			String email,
			String name,
			Integer lowerAge,
			Integer upperAge,
			Date lowerDate,
			Date upperDate,
			Gender gender){
		
		
		List<UserEntity> users; //= new ArrayList<UserEntity>();
		
		users = userEntityService.searchForUsers(
				username,
				email,
				name,
				lowerAge,
				upperAge,
				lowerDate,
				upperDate,
				gender);
		
		return users;
	}
}