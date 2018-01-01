package com.fuqiang.jedis;

import redis.clients.jedis.Jedis;

public class JedisString {
	/**
	 * 针对redis中的字符类型练习
	 */
	static final Jedis jedis = new Jedis();
	public static void main(String[] args) {
		//插入一些用户
		jedis.flushAll();
		set("张三","1,张三,男,汉族");
		set("李四","2,李四,男,汉族");
		set("王五","3,王五,男,汉族");
		set("赵六","4,赵六,男,汉族");
		set("钱二","5,钱二,男,汉族");
		set("王老五","6,王老五,男");
		System.out.println(get("王老五"));
		append("王老五",",汉族");
		System.out.println(get("王老五"));
	}
	/**
	 * 封装的set方法往redis中存储
	 * @param key
	 * @param value
	 */
	public static void set(String key,String value){
		jedis.set(key,value);
	}
	/**
	 * 根据键返回值
	 * @param key
	 * @return
	 */
	public static String get(String key){
		return jedis.get(key);
	}
	/**
	 * 根据指定键追加
	 * @param key
	 * @param value
	 */
	public static void append(String key,String value){
		jedis.append(key, value);
	}
	/**
	 * 设置新值，并且覆盖旧值
	 * @param key
	 * @param value
	 */
	public static void getSet(String key,String value){
		jedis.getSet(key, value);
	}
	/**
	 * 设置变量并且设置存活时间
	 * @param key
	 * @param seconds
	 * @param value
	 */
	public static void setex(String key,int seconds,String value){
		jedis.setex(key, seconds, value);
	}
	/**
	 * key存在就赋值，否则不操作
	 * @param key
	 * @param value
	 */
	public static void setnx(String key,String value){
		jedis.setnx(key, value);
	}
	/**
	 * 返回key的值长度
	 * @param key
	 */
	public static void strlen(String key){
		jedis.strlen(key);
	}
}
