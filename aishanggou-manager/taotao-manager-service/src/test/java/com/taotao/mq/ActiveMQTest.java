package com.taotao.mq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.Test;
/**
 * 
 * 	@Description:测试ActiveMQ
 *	@ClassName:ActiveMQTest
 * 	@author:JavousAiMe
 *	@date:2018年1月3日上午10:18:20
 *  @verson:1.0
 */

public class ActiveMQTest {
	
	
	/**
	 * 
	 * @Title:生产者
	 * @Description:MessageProducer
	 * 
	 */
	@Test
	public void testQueueProducer() throws Exception{
		//1.创建一个连接工具ConnectionFactory 对象 指定服务的ip 和端口号
		ConnectionFactory connectionFactory = new  ActiveMQConnectionFactory("tcp://192.168.149.135:61616");
		//2.使用connectionFactory对象创建一个connection
		Connection connection = connectionFactory.createConnection();
		//3开启连接,调用start()
		connection.start();
		//4.使用connection对象创建一个session对象,高并发情况下分布式事务也用的少了
		//arg0 :是否开启事务 一般不开启 当参数为false时  第二个参数才有意义
		//arg1: 消息的应答模式 手动与自动应答 ,一般使用自动应答 
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5.使用session 对象创建一个producer对象,使用session对象创建一个destnation 目的地有两种queue topic 
		//需要一个目的地  queue  extend Destination
		Queue queue = session.createQueue("test-queue");
		MessageProducer producer = session.createProducer(queue);
		//7.使用producer发送消息
		TextMessage textMessage = new ActiveMQTextMessage();
		textMessage.setText("使用activeMQ 测试 发送队列 点对点的消息");
		//核心api 
		producer.send(textMessage);
		//8.关闭资源
		producer.close();
		session.close();
		connection.close();
	}
	
	/**
	 * 
	 * @Title:消费者
	 * @Description:
	 */
	@Test
	public void testQueueCusome() throws Exception{
		//1.创建一个连接工具ConnectionFactory 对象 指定服务的ip 和端口号
		ConnectionFactory connectionFactory = new  ActiveMQConnectionFactory("tcp://192.168.149.135:61616");
		//2.使用connectionFactory对象创建一个connection
		Connection connection = connectionFactory.createConnection();
		//3开启连接,调用start()
		connection.start();
		//4.使用connection对象创建一个session对象,高并发情况下分布式事务也用的少了
		//arg0 :是否开启事务 一般不开启 当参数为false时  第二个参数才有意义
		//arg1: 消息的应答模式 手动与自动应答 ,一般使用自动应答 
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5.使用session 对象创建一个producer对象,使用session对象创建一个destnation 目的地有两种queue topic 
		//需要一个目的地  queue  extend Destination
		Queue queue = session.createQueue("test-queue");
		MessageConsumer consumer = session.createConsumer(queue);
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				// TODO Auto-generated method stub
			TextMessage textMessage = (TextMessage)message;
			
			String text = "";
					
			try {
				text = textMessage.getText();
				System.out.println(text);
			} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		/*while(true){
			Message message = consumer.receive(3000);
			if(message==null){
				break;
			}
				
		}*/
		consumer.close();
		session.close();
		connection.close();
	}
	

}
