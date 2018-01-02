package com.aishanggou.content.service;

import java.util.List;

import com.aishanggou.common.pojo.EasyUITreeNode;
import com.aishanggou.common.pojo.TaotaoResult;

public interface ContentCategoryService {

	List<EasyUITreeNode> getContentCatList(long parentId);
	TaotaoResult insertContentCat(long parentId, String name);
}
