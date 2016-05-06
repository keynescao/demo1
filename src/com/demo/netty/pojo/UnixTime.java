package com.demo.netty.pojo;

import java.util.Date;

public class UnixTime {

	private final int value;
	
	public UnixTime(){
		this((int)(System.currentTimeMillis() / 1000L + 2208988800L));
	}
	
	public UnixTime(int value){
		this.value = value;
	}

	@Override
	public String toString() {
		return new Date((getValue() - 2208988800L) * 1000L).toString();
	}

	public int getValue() {
		return value;
	}	
	
}
