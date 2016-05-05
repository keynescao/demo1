package com.demo.mq1;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MQUtils {
	
	private MQUtils(){}
	private ConnectionFactory factory;
	private static MQUtils mqInstance = null;
	public static MQUtils getInstance(){
		if(mqInstance == null){	
			mqInstance = new MQUtils();
		}
		return mqInstance;
	}
	public ConnectionFactory getFactory() {
		if(factory == null){
			System.out.println(">>>>>>>>>>>>>>>>>>>.");
			factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD,"tcp://172.30.9.22:61616");//创建工厂实例; 
		}
		return factory;
	}
}
