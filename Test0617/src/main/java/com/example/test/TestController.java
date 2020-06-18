package com.example.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/{num}")
	public String index1(@PathVariable int num, Model model) {
		int res = num;
		model.addAttribute("msg", "num : " + res);
		return "index";
	}
	
	@RequestMapping("/{num}/{operation}/{num1}")
	public String index2(@PathVariable int num,@PathVariable int num1,@PathVariable String operation, Model model) {
		int cal = 0;
		if(operation.equals("add")) {
			cal = num + num1;
		}else if(operation.equals("sub")) {
			cal = num - num1;
		}else if(operation.equals("mul")) {
			cal = num * num1;
		}else if(operation.equals("div")) {
			cal = num / num1;
		}
		model.addAttribute("msg", "num : " + cal);
		return "index";
	}
	

	
	@RequestMapping("/add")
	public String add(@RequestParam("num1") int num1, @RequestParam("num2") int num2, Model model) {
		int cal = num1 + num2;
		model.addAttribute("msg", "num : " + cal);
		return "index";
	}
	@RequestMapping("/sub")
	public String sub(@RequestParam("num1") int num1, @RequestParam("num2") int num2, Model model) {
		int cal = num1 - num2;
		model.addAttribute("msg", "num : " + cal);
		return "index";
	}
	@RequestMapping("/mul")
	public String mul(@RequestParam("num1") int num1, @RequestParam("num2") int num2, Model model) {
		int cal = num1 * num2;
		model.addAttribute("msg", "num : " + cal);
		return "index";
	}
	@RequestMapping("/div")
	public String div(@RequestParam("num1") int num1, @RequestParam("num2") int num2, Model model) {
		int cal = num1 / num2;
		model.addAttribute("msg", "num : " + cal);
		return "index";
	}

}
