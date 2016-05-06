package com.demo.netty.server;

import com.demo.netty.server.handler.DiscardServerHandler;
import com.demo.netty.server.handler.TimeServerHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ServerMain {

	private int port;
	
	public ServerMain(int port){
		this.port = port;
	}
	
	
	public void run()throws Exception{
		
		EventLoopGroup bossGroup = new NioEventLoopGroup();		//接受连接
		EventLoopGroup workerGroup = new NioEventLoopGroup();	//处理连接
		
		try{
			
			ServerBootstrap b = new ServerBootstrap(); //服务启动辅助类
			b.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.childHandler(new ChannelInitializer<SocketChannel>(){

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					
					//指定处理程序
					ch.pipeline().addLast(new TimeServerHandler());
					
				}
				
			})
			.option(ChannelOption.SO_BACKLOG, 128)
			.childOption(ChannelOption.SO_KEEPALIVE, true);
			
			
			ChannelFuture f = b.bind(port).sync();
			f.channel().closeFuture().sync();

			
		}finally{
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}

	}
	
	public static void main(String[] args)throws Exception {
		
		
		new ServerMain(8080).run();
		
		
		
	}

}
