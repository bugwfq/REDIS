package com.fuqiang.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * java连接Redis 事务的练习
 * @author Administrator
 *
 */
public class JedisTransactions {
	//创建链接Redis的连接
	static final Jedis jedis = new Jedis();
	@SuppressWarnings("null")
	public static void main(String[] args) {
		
		//创建一个事务
		Transaction tc = jedis.multi();
		tc.flushAll();
		//添加一个值
		tc.set("a", "1");
		tc.set("b", "2");
		tc.set("c", "3");
		tc.set("d", "4");
		String str = null;
		try{//制造异常
			System.out.println(str.equals(""));
		}catch(Exception e){
			//提交事务
			tc.exec();
			//提交实物后无法查看设置的该值
			tc.set("e", "5");
		}
		
		
		

	}
}
