package com.szlizarrrr.loginsiliy.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.szlizarrrr.loginsiliy.domain.User;
import com.szlizarrrr.loginsiliy.domain.UserNon;
import com.szlizarrrr.loginsiliy.repository.UserRepository;

@Controller
public class UserController {
	
	public static final String CURRENT_USER = "currentUser";
	
	@Autowired
	UserRepository userRepository;
	
	@Resource
	List<UserNon> mockDb;
	
	@GetMapping("/home")
	public String openHomePage() {
		return "home";
	}
	
	@GetMapping("/loginin")
	public String openLogininPage() {
		return "loginin";
	}
	
	@PostMapping("/loginin")
	public String loginin(UserNon user, HttpSession session) {
		for(UserNon element : mockDb) {
			if(element.getUsername().equals(user.getUsername())) {
				if(element.getPassword().equals(user.getPassword())) {
					session.setAttribute(CURRENT_USER, user);
					return "redirect:/home";
				}
				else
					return "redirect:/loginin?passwordwrong";
			}
		}
		return "redirect:/loginin?notexist";
	}
	
	@GetMapping("/login")
	public String openLoginPage() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(User user) {
		if(userRepository.findByUsername(user.getUsername()) == null) return "redirect:/login?notexist";
		if(userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()) == null) return "redirect:/login?passwordwrong";
		return "redirect:/home?user=" + user.getUsername();
	}
	
	@GetMapping("/register")
	public String openRegisterPage() {
		return "register";
	}
	
	@PostMapping("/register")
	@Transactional
	public String registerNewUser(User user) {
		if(userRepository.findByUsername(user.getUsername()) != null) return "redirect:/register?error";
		userRepository.save(user);
		return "redirect:/login";
	}

}
