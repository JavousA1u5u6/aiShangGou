package com.aishanggou.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;


@Controller
public class ContentCategoryController {

	
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	/**
	 * 接收值:id
	 * 返回值:EasyUITreeNode
	 * @return
	 */
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeNode>  getContentCatList(@RequestParam(value="id",defaultValue="0")long parentId){
		
		List<EasyUITreeNode> list = contentCategoryService.getContentCatList(parentId);
		return list;
		
	}
	
	/**
	 * 接受值:parentid name
	 * 返回值:返回一个新id  taotaoresult
	 */
	@RequestMapping("/content/category/create")
	@ResponseBody
	public TaotaoResult insertContentCat(Long parentId,String name ){
		TaotaoResult taotaoResult = contentCategoryService.insertContentCat(parentId, name);
		
		return taotaoResult;
	}
	
}
