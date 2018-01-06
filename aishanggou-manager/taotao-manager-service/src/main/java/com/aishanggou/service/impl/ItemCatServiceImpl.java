package com.aishanggou.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aishanggou.common.pojo.EasyUITreeNode;
import com.aishanggou.mapper.TbItemCatMapper;
import com.aishanggou.po.TbItemCat;
import com.aishanggou.po.TbItemCatExample;
import com.aishanggou.po.TbItemCatExample.Criteria;
import com.aishanggou.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	
	private static Logger logger =  LoggerFactory.getLogger(ItemCatServiceImpl.class);
	
	@Override
	public List<EasyUITreeNode> getItemsCatList(long parentId) {
		// TODO Auto-generated method stub
		
		//此对象就是专门用于 封装查询条件
		TbItemCatExample catExample = new TbItemCatExample();
		
		//当需要增加条件进行查询时  可以创建一个criteria对象 来封装条件数据
		Criteria createCriteria = catExample.createCriteria();
		
		createCriteria.andParentIdEqualTo(parentId);
		
		List<TbItemCat> list = tbItemCatMapper.selectByExample(catExample);
		
		List<EasyUITreeNode> treeNodes = new ArrayList<EasyUITreeNode>();
		for (TbItemCat tbItemCat : list) {
			com.taotao.common.pojo.EasyUITreeNode easyUITreeNode = new EasyUITreeNode();
			easyUITreeNode.setId(tbItemCat.getId());
			easyUITreeNode.setText(tbItemCat.getName());
			//这里注意一个小细节 在set值的时候 也可以对值进行三元判断
			easyUITreeNode.setState(tbItemCat.getIsParent()?"close":"open");
			treeNodes.add(easyUITreeNode);
		}
		
		logger.info("节点装载完毕");
	
		
		return treeNodes;
	}

}
