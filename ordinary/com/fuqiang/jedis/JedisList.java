package com.fuqiang.jedis;

import redis.clients.jedis.Jedis;

public class JedisList {
	static Jedis jedis = new Jedis();
	public static void main(String[] args){
		//
	}
	/**
	 * ��list���ϵ�������һ��Ԫ��
	 * @param key
	 * @param str
	 */
	public static void lpush(String key ,String str){
		jedis.lpush(key, str);
		jedis.lpop(key);
	}
}
