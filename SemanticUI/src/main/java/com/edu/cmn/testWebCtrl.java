package com.edu.cmn;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class testWebCtrl {
	private String webUrl = "test"; 
	
	@ResponseBody
	@RequestMapping(value = "testpage")
	public String test() {
		return "test";
	}
}
