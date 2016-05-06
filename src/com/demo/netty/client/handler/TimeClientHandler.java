package com.demo.netty.client.handler;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeClientHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		
		ByteBuf m = (ByteBuf)msg;
		try{
			
			long currentMills = (m.readUnsignedInt() - 2208988800L) * 1000L;
			System.out.println(new Date(currentMills));
			ctx.close();
			
			
		}finally{
			m.release();
		}
		
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	
	
	
}
