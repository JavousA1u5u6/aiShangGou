package com.taotao.mybatis.pagehelper;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aishanggou.mapper.TbItemMapper;
import com.aishanggou.po.TbItem;
import com.aishanggou.po.TbItemExample;
import com.aishanggou.po.TbItemExample.Criteria;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 测试mybatis分页插件 pageHelper的使用
 * @author JavousAiMe
 *
 */
public class PageHelperTest {
	
	@Test
	public void testPageHelper() throws Exception{
		
		//初始化容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
	
		//获取代理对象
		TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
		
		
		//执行查询
		TbItemExample example = new TbItemExample();
		
		Criteria criteria = example.createCriteria();
		example.setOrderByClause("updated desc");
		
		//分页处理  threadlocal与当前线程绑定
		PageHelper.startPage(1, 30);
		//取分页信息
		List<TbItem> list = itemMapper.selectByExample(example);
		
		System.out.println("查到的记录数:"+list.size());
		
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		
		System.out.println(pageInfo.getTotal());
		
	}

}
