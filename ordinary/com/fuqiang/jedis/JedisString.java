package com.fuqiang.jedis;

import redis.clients.jedis.Jedis;

public class JedisString {
	/**
	 * ���redis�е��ַ�������ϰ
	 */
	static final Jedis jedis = new Jedis();
	public static void main(String[] args) {
		//����һЩ�û�
		jedis.flushAll();
		set("����","1,����,��,����");
		set("����","2,����,��,����");
		set("����","3,����,��,����");
		set("����","4,����,��,����");
		set("Ǯ��","5,Ǯ��,��,����");
		set("������","6,������,��");
		System.out.println(get("������"));
		append("������",",����");
		System.out.println(get("������"));
	}
	/**
	 * ��װ��set������redis�д洢
	 * @param key
	 * @param value
	 */
	public static void set(String key,String value){
		jedis.set(key,value);
	}
	/**
	 * ���ݼ�����ֵ
	 * @param key
	 * @return
	 */
	public static String get(String key){
		return jedis.get(key);
	}
	/**
	 * ����ָ����׷��
	 * @param key
	 * @param value
	 */
	public static void append(String key,String value){
		jedis.append(key, value);
	}
	/**
	 * ������ֵ�����Ҹ��Ǿ�ֵ
	 * @param key
	 * @param value
	 */
	public static void getSet(String key,String value){
		jedis.getSet(key, value);
	}
	/**
	 * ���ñ����������ô��ʱ��
	 * @param key
	 * @param seconds
	 * @param value
	 */
	public static void setex(String key,int seconds,String value){
		jedis.setex(key, seconds, value);
	}
	/**
	 * key���ھ͸�ֵ�����򲻲���
	 * @param key
	 * @param value
	 */
	public static void setnx(String key,String value){
		jedis.setnx(key, value);
	}
	/**
	 * ����key��ֵ����
	 * @param key
	 */
	public static void strlen(String key){
		jedis.strlen(key);
	}
}
