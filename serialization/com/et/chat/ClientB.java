package com.et.chat;


import java.util.Scanner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class ClientB {

	static final Jedis jedis = new Jedis();
	public static void main(String[] args){
		BReadThread read = new BReadThread();
		read.setDaemon(true);
		read.start();
		jedis.subscribe(new JedisPubSub() {
			@Override
			public void onMessage(String channel,String message) {
				System.out.println(message);
				super.onMessage(channel,message);
			};
		} ,Constant.KEY_A);
		
	}
	
}
class BReadThread extends Thread{
	public static Scanner input = new Scanner(System.in);
	static final Jedis jedis = new Jedis();
	@Override
	public void run() {
		boolean bool = true;
		while(bool){
			System.out.println("«Î ‰»Î£∫");
			String message = input.next();
			jedis.publish(Constant.KEY_B, message);
		}
		
	}
	
}