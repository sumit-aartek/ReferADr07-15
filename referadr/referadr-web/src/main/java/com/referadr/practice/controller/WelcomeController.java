package com.referadr.practice.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

	@RequestMapping("/welcome")
	public String welcomePage(Map<String, Object> map){
	
		return "firstScreen";
	}
}
