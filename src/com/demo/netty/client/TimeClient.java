package com.demo.netty.client;

import com.demo.netty.client.handler.TimeClientHandler;
import com.demo.netty.client.handler.TimeDecoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeClient {

	public static void main(String[] args) throws Exception{

		EventLoopGroup worker = new NioEventLoopGroup();
		
		try{
			
			Bootstrap b = new Bootstrap();
			b.group(worker)
			.channel(NioSocketChannel.class)
			.option(ChannelOption.SO_KEEPALIVE, true)
			.handler(new ChannelInitializer<SocketChannel>(){

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new TimeDecoder(),new TimeClientHandler());
				}
				
			});
			
			ChannelFuture f = b.connect("localhost", 8080).sync();
			f.channel().closeFuture().sync();
			
		}finally{
			worker.shutdownGracefully();
		}
		
	}

}
