package com.taotao.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.po.TbContentCategory;
import com.taotao.po.TbContentCategoryExample;
import com.taotao.po.TbContentCategoryExample.Criteria;

/**
 * 内容分类管理服务
 * <p>Title: ContentCategoryServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	
	@Override
	public List<EasyUITreeNode> getContentCatList(long parentId) {
		//根据parentid查询子节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		//设置查询条件
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		//返回结果List
		List<EasyUITreeNode> resultList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			//添加到列表
			resultList.add(node);
		}
		return resultList;
	}

	@Override
	public TaotaoResult insertContentCat(long parentId, String name) {
		//创建一个内容分类对象
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setName(name);
		contentCategory.setParentId(parentId);
		//新添加的节点都是叶子节点
		contentCategory.setIsParent(false);
		//排序方法默认为1
		contentCategory.setSortOrder(1);
		//1(正常),2(删除)
		contentCategory.setStatus(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		//插入节点
		contentCategoryMapper.insert(contentCategory);
		//判断父节点是否为叶子节点
		TbContentCategory parnetNode = contentCategoryMapper.selectByPrimaryKey(parentId);
		if(!parnetNode.getIsParent()) {
			parnetNode.setIsParent(true);
			//更新父节点
			contentCategoryMapper.updateByPrimaryKey(parnetNode);
		}
		/*TbContentCategory tbContentCategory = new TbContentCategory();
		tbContentCategory.setId(parentId);
		tbContentCategory.setIsParent(true);
		//此修改是只对有值的数据进行修改
		contentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);*/
		//当然也可以不要这一步 直接进行修改  可以减少对数据库的读取 直接通过主键修改
		//需要将 生成id更新回 节点上  方便后续修改
		return TaotaoResult.ok(contentCategory);
	}
	
	
	@Override
	public TaotaoResult deleteContentCat(Long id) {
		// 删除
		
		//1.根据id查询此节点信息
		TbContentCategory tbContentCategory = contentCategoryMapper.selectByPrimaryKey(id);
		//判断此节点是否是父节点
		deleteById( tbContentCategory);
		return TaotaoResult.ok(null);
	}

	private void deleteById( TbContentCategory tbContentCategory) {
		Boolean isParent = tbContentCategory.getIsParent();
		if(isParent!=true){
			//不是父节点 直接删除
			contentCategoryMapper.deleteByPrimaryKey(tbContentCategory.getId());
		}else{
			TbContentCategoryExample example = new TbContentCategoryExample();
			Criteria criteria = example.createCriteria();
			criteria.andParentIdEqualTo(tbContentCategory.getId());
			//是父节点 需要查询它所有的子节点
			List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
			//遍历 判断是子节点 还是父节点
			for (TbContentCategory tbContentCategory2 : list) {
				Boolean isParent2 = tbContentCategory2.getIsParent();
				//此处使用递归
				deleteById( tbContentCategory2);
			}
		}
	}
	

}
