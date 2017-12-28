package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.po.TbItem;

public interface ItemService {
	
	List<TbItem> geTbItems();
	
	EasyUIDataGridResult getItemsList(int page,Integer sizes);
	
	void  updateItem(TbItem tbItem);
	
	TaotaoResult addItem(TbItem item ,String desc);
	
	
	
	

}
