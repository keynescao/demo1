package com.demo.mq1;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Publisher {

	public static void main(String[] args) {

		ConnectionFactory factory = MQUtils.getInstance().getFactory(); //jms工厂
		Connection connection = null; //连接
		Session session; //会话
		Destination destination; //消息目的地
		MessageProducer producer; //消息发送者

		
		try{
			
			connection = factory.createConnection();
			connection.start();
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("rdp_import_queue");
			
			
			producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			
			
			for(int i = 0;i<10;i++){
				String msg = "echo message hello io " + i + "-" + System.currentTimeMillis();
				System.out.println("send msg:" + msg);
				TextMessage textMessage = session.createTextMessage(msg);
				producer.send(textMessage);
			}
			producer.close();
			session.commit();

		}catch(JMSException ex){
			ex.printStackTrace();
		}finally{
			try {
				if(connection!=null){
					connection.close();
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}

	}
}
