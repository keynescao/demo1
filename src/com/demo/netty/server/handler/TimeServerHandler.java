package com.demo.netty.server.handler;

import com.demo.netty.pojo.UnixTime;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeServerHandler extends ChannelHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		
		/*//建立连接准备通信时发生
		final ByteBuf time = ctx.alloc().buffer(4);
		int msg = (int)(System.currentTimeMillis()/1000L + 2208988800L);
		System.out.println("Msg: " + msg);
		time.writeInt(msg);
		
		final ChannelFuture f = ctx.writeAndFlush(time);//future 模式异步处理消息发送
		//注册匿名类监听事件完成后关闭连接
		f.addListener(new ChannelFutureListener(){

			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				assert f == future;
				ctx.close();
			}
			
		});*/
		
		
		
		UnixTime t = new UnixTime();
		System.out.println("===" + t);
		ChannelFuture f = ctx.writeAndFlush(t);
		f.addListener(ChannelFutureListener.CLOSE);
		
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	
}
