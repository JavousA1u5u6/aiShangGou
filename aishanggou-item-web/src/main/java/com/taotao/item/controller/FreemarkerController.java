package com.taotao.item.controller;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Controller
public class FreemarkerController {

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	
	@RequestMapping("/genHtml")
	@ResponseBody
	public String genHtml() throws Exception{
		/**
		 * 使用freeaker模板引擎来生成静态页面
		 * 需要1:template+数据集 
		 * 需要2:输出流  以及输出的目录
		 */
		Configuration configuration = freeMarkerConfigurer.getConfiguration();
		Template template = configuration.getTemplate("hello.ftl");
		Map map = new HashMap<>();
		map.put("hello", "freemarker 整合 spring 测试");
		FileWriter out = new FileWriter(new File("C:\\temp\\freemarker\\out.html"));
		template.process(map, out);
		out.close();
		return "ok";
	}
}
