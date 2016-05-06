package com.demo.netty.client.handler;

import java.util.Date;

import com.demo.netty.pojo.UnixTime;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class TimeClientHandler extends ChannelHandlerAdapter {

	/*private ByteBuf buf;//声明一块缓冲区
	
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		//有连接加入时,初使化缓冲区大小		
		buf = ctx.alloc().buffer(4);
		System.out.println("connection   one.");
	}
	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		buf.release();
		buf  =  null;
		System.out.println("somebody   leave.");
	}*/
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		

		UnixTime t = (UnixTime)msg;
		System.out.println(t);
		ctx.close();
		/*ByteBuf m = (ByteBuf)msg;
		
		try{
			long t = m.readUnsignedInt();
			System.out.println("===" + t);
			long currentMills = (t - 2208988800L) * 1000L;
			System.out.println(new Date(currentMills));
			ctx.close();
			
			
		}finally{
			m.release();
		}*/
		
		
		/*buf.writeBytes(m);
		m.release();
		System.out.println(buf.capacity()-buf.writerIndex());
		if(buf.readableBytes() >=4){
			long currentMills = (buf.readInt() - 2208988800L) * 1000L;
			System.out.println(new Date(currentMills));
			ctx.close();
		}*/
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	
	
	
}
