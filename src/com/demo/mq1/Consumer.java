package com.demo.mq1;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Consumer{

	public static void main(String[] args)throws Exception {

		ConnectionFactory factory = MQUtils.getInstance().getFactory(); //jms工厂
		Connection connection = factory.createConnection(); //连接
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); //会话
		Destination destination = session.createQueue("rdp_import_queue"); //消息目的地
		
		MessageConsumer consumer = session.createConsumer(destination);//消费者
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message msg) {
				try {
					System.out.println("receive: " +((TextMessage)msg).getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		
		
		/*while(true){
			TextMessage textMessage = (TextMessage)consumer.receive();
			//System.out.println(textMessage);
			if(textMessage != null && !textMessage.getJMSRedelivered()){
				System.out.println("receive:"+ textMessage.getText());
				textMessage.acknowledge();//收到
			}
			Thread.sleep(1000);
		}*/
		/*session.commit();
		session.close();*/
		//connection.close();
		
		
	}
	
	
	
}

