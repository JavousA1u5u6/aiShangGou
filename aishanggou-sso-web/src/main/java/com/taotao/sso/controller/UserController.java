package com.taotao.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.sso.service.UserRegisterService;

/**
 * 用户管理Controller
 * <p>Title: UserController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
@Controller
public class UserController {

	@Autowired
	private UserRegisterService userRegisterService;
	
	@RequestMapping(value="/user/check/{param}/{type}", method=RequestMethod.GET)
	@ResponseBody
	public TaotaoResult checkUserInfo(@PathVariable String param, @PathVariable Integer type) {
		TaotaoResult result = userRegisterService.checkUserInfo(param, type);
		return result;
	}
}
