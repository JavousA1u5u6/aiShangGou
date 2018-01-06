package com.taotao.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.item.pojo.Item;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	public String showItemInfo(@PathVariable Long itemId, Model model) {
		
		try {
			//从缓存中获取   如果没有去数据库拆线呢
		} catch (Exception e) {
			// TODO: handle exception
		}
		//根据商品Id查询商品基本信息
		TbItem tbItem = itemService.getItemById(itemId);
		//使用Tbitem初始化item对象 
		/*Item item = new Item(tbItem);
		//根据商品id查询商品描述
		TbItemDesc tbItemDesc = itemService.getItemDescById(itemId);
		//传递给页面
		model.addAttribute("item", item);
		model.addAttribute("itemDesc", tbItemDesc);
		//返回逻辑视图
		return "item";*/
		try {
			//查询到之后加入缓存中 使用string类型 并设置过期时间 
		} catch (Exception e) {
			// TODO: handle exception
		}
		Item  item = new Item(tbItem);
		
		//根据商品id拆线呢描述信息
		model.addAttribute("item", item);
		model.addAttribute("itemDesc", itemDesc);
		//因为考虑到每次商品都需要 去数据库查询 所以需要进行优化  在查询之前先去缓存中 获取
		
		return "item";
	}
	
}
