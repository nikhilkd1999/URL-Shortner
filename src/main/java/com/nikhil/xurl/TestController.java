package com.nikhil.xurl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	
	@GetMapping("/")
	@ResponseBody
	public String getResponce()
	{
		return "Avni";
	}

}