package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.service.ItemService;

@RequestMapping("/item")
@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/list")
	//定义此注解即可将 响应json
	@ResponseBody
	/**
	 * 1.接收页面请求 url
	 * 2.绑定参数 page sizes
	 * 3.发送参数 retun:json 
	 * @return
	 */
	public EasyUIDataGridResult showItemList(int page,Integer rows){
		
		EasyUIDataGridResult dataGridResult = itemService.getItemsList(page, rows);
		
		//这里需要注意 因为传递的时 json  所以需要将 pojo类 序列化
		return dataGridResult;
	}

}
