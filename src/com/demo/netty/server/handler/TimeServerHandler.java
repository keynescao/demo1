package com.demo.netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeServerHandler extends ChannelHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		
		//建立连接准备通信时发生
		final ByteBuf time = ctx.alloc().buffer(4);
		time.writeInt((int)(System.currentTimeMillis()/1000L + 2208988800L));
		
		final ChannelFuture f = ctx.writeAndFlush(time);//future 模式异步处理消息发送
		//注册匿名类监听事件完成后关闭连接
		f.addListener(new ChannelFutureListener(){

			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				assert f == future;
				ctx.close();
			}
			
		});
		
		
		
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	
}
