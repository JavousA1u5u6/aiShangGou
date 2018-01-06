package com.taotao.item.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.aishanggou.po.TbItem;
import com.aishanggou.service.ItemService;
import com.taotao.item.pojo.Item;

public class HtmlGenListner2 implements MessageListener {
	
	//这里需要调取manage service的服务 去查询商品信息  
	//同时要掉用服务 就需要去注册中心  zookeeper 
	@Autowired
	private ItemService ItemService;
	
	
	
	

	@Override
	public void onMessage(Message message) {
		// 从消息队列中获取消息
		
		TextMessage textMessage = (TextMessage)message;
		try {
			String strId = textMessage.getText();
			//将string 类型的 转为long类型
			Long id = Long.valueOf(strId);
			
			//根据id去数据库查询商品信息
		TbItem tbItem	= ItemService.getTbItem(id);
			//将此类型的值 作为构造器参数  用来构造我们所定义的pojo 
		Item item = new Item(tbItem);
			//调用freemaker模板引擎 生成html静态页面
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
