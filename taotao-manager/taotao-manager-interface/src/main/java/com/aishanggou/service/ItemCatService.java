package com.aishanggou.service;

import java.util.List;

import com.aishanggou.common.pojo.EasyUITreeNode;

public interface ItemCatService {

	List<EasyUITreeNode> getItemsCatList(long parentId);
}
