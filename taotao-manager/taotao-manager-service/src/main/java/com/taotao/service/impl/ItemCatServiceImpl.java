package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.po.TbItemCat;
import com.taotao.po.TbItemCatExample;
import com.taotao.po.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	
	@Override
	public List<EasyUITreeNode> getItemsCatList(long parentId) {
		// TODO Auto-generated method stub
		
		TbItemCatExample catExample = new TbItemCatExample();
		
		//当需要增加条件进行查询时  可以创建一个criteria对象 来封装条件数据
		Criteria createCriteria = catExample.createCriteria();
		
		createCriteria.andParentIdEqualTo(parentId);
		
		List<TbItemCat> list = tbItemCatMapper.selectByExample(catExample);
		
		List<EasyUITreeNode> treeNodes = new ArrayList<EasyUITreeNode>();
		for (TbItemCat tbItemCat : list) {
			EasyUITreeNode easyUITreeNode = new EasyUITreeNode();
			easyUITreeNode.setId(tbItemCat.getId());
			easyUITreeNode.setText(tbItemCat.getName());
			easyUITreeNode.setState(tbItemCat.getIsParent()?"closed":"open");
			treeNodes.add(easyUITreeNode);
		}
	
		
		return treeNodes;
	}

}
