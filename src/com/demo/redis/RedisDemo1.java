package com.demo.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
public class RedisDemo1 {
	
	static ShardedJedisPool  pool;
	static{
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxActive(10);
		config.setMaxIdle(1000*60);
		config.setMaxWait(1000L*10);
		config.setTestOnBorrow(true);
		
		List<JedisShardInfo> list = new ArrayList<JedisShardInfo>(2);
		list.add(new JedisShardInfo("192.168.132.130",7001));
		list.add(new JedisShardInfo("192.168.132.130",7002));
		list.add(new JedisShardInfo("192.168.132.131",7001));
		list.add(new JedisShardInfo("192.168.132.131",7002));
		list.add(new JedisShardInfo("192.168.132.132",7001));
		list.add(new JedisShardInfo("192.168.132.132",7002));
		
		pool = new ShardedJedisPool(config, list);
		
	}
	
	
	public static void testDemo1(){
		ShardedJedis jds = null;
		try{
			jds = pool.getResource();
			
			//jds.set("uuid", String.valueOf(UUID.randomUUID()));
			//jds.disconnect();
			System.out.println(jds.get("uuid"));
		}finally{
			pool.returnResource(jds);
		}
		//pool.destroy();
	}
	

	public static void main(String[] args) throws Exception{
		
		
		testDemo1();
		
	}

}
