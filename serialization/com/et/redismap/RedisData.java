package com.et.redismap;

import redis.clients.jedis.Jedis;

public class RedisData {
	public static final Jedis jedis = new Jedis();;
	public static final String KEY_NAME = "class1607";
	public static void main(String[] args){
		FileProcessing my = new FileProcessing();
		my.fileIn(jedis, KEY_NAME);
		my.fileOut(jedis, KEY_NAME);
	}
}
