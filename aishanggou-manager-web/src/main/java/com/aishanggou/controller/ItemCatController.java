package com.aishanggou.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aishanggou.common.pojo.EasyUITreeNode;
import com.aishanggou.service.ItemCatService;

@Controller
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping("/item/cat/list")
	@ResponseBody
	/**
	 * 为什么接收的值是id  而不是parentid
	 * 因为easyui tree 底层没有加载子节点时  默认传递的值命名未id
	 * @param parentId
	 * @return
	 */
	public List<EasyUITreeNode> getItemCatList(@RequestParam(value="id",defaultValue="0") Long parentId){
		//如果什么都没有传递 如何响应一个默认值  使用defaultValue
		List<EasyUITreeNode> list = itemCatService.getItemsCatList(parentId);
		return list;
	}

}
