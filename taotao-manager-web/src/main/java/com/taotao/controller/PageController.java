package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//窄化请求路径
public class PageController {
	
	@RequestMapping("/")
	public String showIndex(){
		return "index";
	}
	
	
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page){
		
		return page;
	}
	
	

}
