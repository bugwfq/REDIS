package com.fuqiang.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * java����Redis �������ϰ
 * @author Administrator
 *
 */
public class JedisTransactions {
	//��������Redis������
	static final Jedis jedis = new Jedis();
	@SuppressWarnings("null")
	public static void main(String[] args) {
		
		//����һ������
		Transaction tc = jedis.multi();
		tc.flushAll();
		//���һ��ֵ
		tc.set("a", "1");
		tc.set("b", "2");
		tc.set("c", "3");
		tc.set("d", "4");
		String str = null;
		try{//�����쳣
			System.out.println(str.equals(""));
		}catch(Exception e){
			//�ύ����
			tc.exec();
			//�ύʵ����޷��鿴���õĸ�ֵ
			tc.set("e", "5");
		}
		
		
		

	}
}
