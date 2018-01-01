package com.et.redis;

import redis.clients.jedis.Jedis;

/**
 * Redis»º´æÇø
 */
public class RedisBuffer {
	static Jedis jedis = null;
	static FileDispose file;
	static{
		jedis = new Jedis();
		file = new FileDispose();
	}
	public static void add(User user){
		byte[] a= SerializeDispose.serialize(user);
		jedis.set(user.getName().getBytes(),a);
	}
	public static User get(String key){
		User u = null;
		if(jedis.keys(key).size()>0){
			byte[] value = jedis.get(key.getBytes());
			u = (User)SerializeDispose.unserialize(value);
			return u;
		}
		u = file.fileInput(key);
		add(u);
		return u;
	}
}
