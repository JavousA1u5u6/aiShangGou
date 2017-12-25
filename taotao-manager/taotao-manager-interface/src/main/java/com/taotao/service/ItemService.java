package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.po.TbItem;

public interface ItemService {
	
	List<TbItem> geTbItems();
	
	EasyUIDataGridResult getItemsList(int page,Integer sizes);

}
