package com.taotao.mq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SpringMQTest {
	
	public static void main(String[] args) {
		
		//从springMVC提供的API获取request
		//正常来说service层 时没有  request和response  如果从controller传过来 又太暴力
		//发现springmvc提供了 一个request上下文容器
		//此api 就是
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		requestAttributes.getAttribute(name, scope);
		HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
		 HttpServletResponse response = ((ServletRequestAttributes)requestAttributes).getResponse();
		
	}

}
