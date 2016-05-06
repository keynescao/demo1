package com.demo.netty.server.handler;

import com.demo.netty.pojo.UnixTime;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

public class TimeEncodeExt extends ChannelHandlerAdapter {

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		
		UnixTime t = (UnixTime)msg;
		ByteBuf encoded = ctx.alloc().buffer(4);
		encoded.writeInt(t.getValue());
		ctx.write(encoded,promise);
		
	}

	
}
