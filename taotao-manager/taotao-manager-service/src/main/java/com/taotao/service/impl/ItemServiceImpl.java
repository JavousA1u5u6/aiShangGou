package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.po.TbItem;
import com.taotao.po.TbItemDesc;
import com.taotao.po.TbItemExample;
import com.taotao.po.TbItemExample.Criteria;
import com.taotao.service.ItemService;


@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private TbItemMapper tbItemMapper;
	
	@Autowired
	private TbItemDescMapper tbItemDescMapper;

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

	@Override
	public void updateItem(TbItem tbItem) {
		// 修改商品
		tbItemMapper.updateByPrimaryKeySelective(tbItem);
	}

	@Override
	public TaotaoResult addItem(TbItem item, String desc) {
		//需要将商品的信息补全  因为商品的描述信息可能数据比较大 所以专门用一张表进行存储
		//进行具体数据的设置 id status 
		long itemId = IDUtils.genItemId();
		item.setId(itemId);
		//设置商品的状态
		item.setStatus((byte)1);
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		// 增加商品
		tbItemMapper.insert(item);
		//存储商品描述
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setItemId(itemId);
		tbItemDesc.setCreated(date);
		tbItemDesc.setUpdated(date);
		//进行商品描述的添加
		tbItemDescMapper.insert(tbItemDesc);
		return TaotaoResult.ok();
	}

}
