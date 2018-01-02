package com.aishanggou.service;

import java.util.List;

import com.aishanggou.common.pojo.EasyUIDataGridResult;
import com.aishanggou.common.pojo.EasyUITreeNode;
import com.aishanggou.common.pojo.TaotaoResult;
import com.aishanggou.po.TbItem;

public interface ItemService {
	
	List<TbItem> geTbItems();
	
	EasyUIDataGridResult getItemsList(int page,Integer sizes);
	
	void  updateItem(TbItem tbItem);
	
	TaotaoResult addItem(TbItem item ,String desc);
	
	
	
	

}
