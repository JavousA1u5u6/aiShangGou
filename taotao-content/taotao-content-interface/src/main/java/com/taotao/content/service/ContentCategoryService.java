package com.taotao.content.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

public interface ContentCategoryService {

	List<EasyUITreeNode> getContentCatList(long parentId);
	TaotaoResult insertContentCat(long parentId, String name);
	TaotaoResult deleteContentCat(Long id);
}
