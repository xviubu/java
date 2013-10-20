package com.xviubu.login.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import java.util.Map;

import com.xviubu.login.service.*;
import com.xviubu.login.domain.User;
@Controller
@RequestMapping("/user")
public class UserController
{
	@Inject
	public UserController(UserService userService)
	{
		this.userService = userService;
	}
	@RequestMapping("/login_success")
	public String showLoginSuccess(Model model)
	{
		return "login_success";
	}
	@RequestMapping(method=RequestMethod.GET,params="new")
	public String createUserProfile(Model model)
	{
		model.addAttribute(new User());
		return "edit";
	}
	@RequestMapping("/login")
	public String showLoginForm()
	{
		return "login";
	}
	@RequestMapping("/reset_password")
	public String showResetPassword()
	{
		return "reset_password";
	}
	@RequestMapping("/resend_forget_email")
	public String sendForgetEmail(@RequestParam("username") String username,@RequestParam("email") String email,Model model)
	{
		User user = userService.getUserByUsername(username);
		if(user.getEmail().equals(email))
		{
			user.sendForgetEmail();
			return "Tip";
		}
		else
		{
			return "notMatch";
		}
	}

	@RequestMapping(value="/save",method=RequestMethod.GET)
	public String saveUserForm(@RequestParam("username") String username, @RequestParam("email") String email,Model model)
	{
		return "save";
	}
	@RequestMapping(value="/saveUser")
	public String saveUser(@RequestParam("username") String username,@RequestParam("password") String password)
	{
		User user = userService.getUserByUsername(username);
		user.setPassword(password);
		userService.saveUser(user);

		return "success";
	}


	@RequestMapping(method=RequestMethod.POST)
	public String addUserFormForm(@Valid User user,BindingResult bindingResult,Map<String,Object> model)
	{
		if(bindingResult.hasErrors())
		{
			return "edit";
		}

		try
		{
			userService.addUser(user);
		}
		catch(IllegalStateException e)
		{
			model.put("UserExist","Username already exists,please chang it");
			return "edit";
		}

		model.put("user",user);
		return "success";
	}

	private UserService userService;
}
