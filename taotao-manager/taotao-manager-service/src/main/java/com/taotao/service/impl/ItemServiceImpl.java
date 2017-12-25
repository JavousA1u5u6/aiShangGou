package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.po.TbItem;
import com.taotao.po.TbItemExample;
import com.taotao.po.TbItemExample.Criteria;
import com.taotao.service.ItemService;


@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private TbItemMapper tbItemMapper;

	@Override
	public List<TbItem> geTbItems() {
		//测试类方法
		return null;
	}

	@Override
	public EasyUIDataGridResult getItemsList(int page, Integer sizes) {
		// 进行分页查询  使用pagehelper 返回EasyUIDataGridResult  最后发布服务
		TbItemExample example = new TbItemExample();
		
		Criteria createCriteria = example.createCriteria();
		
		PageHelper pageHelper = new PageHelper();
		
		pageHelper.startPage(page, sizes);
		
		
		List<TbItem> list = tbItemMapper.selectByExample(example);
		
		EasyUIDataGridResult dataGridResult = new EasyUIDataGridResult();
		
		
		PageInfo<TbItem>  pageInfo = new PageInfo<TbItem>(list);
		
		dataGridResult.setTotal(pageInfo.getTotal());
		dataGridResult.setRows(list);
		
		return dataGridResult;
	}

}
