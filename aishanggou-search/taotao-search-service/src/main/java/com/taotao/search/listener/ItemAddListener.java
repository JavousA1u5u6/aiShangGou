package com.taotao.search.listener;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.common.pojo.SearchItem;
import com.taotao.search.mapper.ItemMapper;
/**
 *为什么发送的时id 而不是商品实例呢?
 *
 * 	因为商品的信息含量数据比较大  如果使用activeMQ 会耗费太多带宽
 * 接收到消息之后  执行此操作   也可能需要生成静态页面 同步缓存
 * 
 * 当后台进行商品的添加时  taotao -manager-service
 * 将添加的数据存入数据库时 会随之而动将此份数据放入消息队列
 * 通过jmsTemplate.send()
 * 
 * 所以设定为消费方法  当增加商品时 便通知搜索系统进行索引库的更新  
 * 监听到有商品增删改的信息时 便进行操作solr
 * taotao-search-service (此层主要负责索引库的增删改查 业务逻辑)
 * 为什么要使用topic 发布/订阅模式
 * 因为在添加完成之后  还需要有别的操作
 * 	@author:JavousAiMe
 *	@date:2018年1月3日下午2:45:47
 *  
 */
public class ItemAddListener implements MessageListener{

	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private SolrServer solrServer;

	@Override
	public void onMessage(Message message) {
		// 从消息中取商品id
		try {
			TextMessage textMessage = (TextMessage) message;
			/*String strItemId = textMessage.getText();
			//转换成Long
			Long itemId = new Long(strItemId);
			//根据商品Id查询商品消息
			SearchItem searchItem = itemMapper.getItemById(itemId);
			//把商品消息添加到索引库
			SolrInputDocument document = new SolrInputDocument();
			// 4、为文档添加域
			document.addField("id", searchItem.getId());
			document.addField("item_title", searchItem.getTitle());
			document.addField("item_sell_point", searchItem.getSell_point());
			document.addField("item_price", searchItem.getPrice());
			document.addField("item_image", searchItem.getImage());
			document.addField("item_category_name", searchItem.getCategory_name());
			document.addField("item_desc", searchItem.getItem_des());
			// 5、向索引库中添加文档。
			solrServer.add(document);
			//提交
			solrServer.commit();*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
