package com.example.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/{num}")
	public String index(@PathVariable int num, Model model) {
		int res = num;
		model.addAttribute("msg", "num : " + res);
		return "index";
	}

}
