package cn.myredis.clin;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class RunClient {
	//scanner ������
	static Scanner input = new Scanner(System.in);
	//��־�����ж��Ƿ����ӳɹ�
	static boolean bool = true;
	//��¼�������ʾ��Ϣ
	static String askingErr;
	//�������ݿ�
	static Jedis jedis; 
	//�л����ݿ�ռ�ʱ��Ϊ�û���ʾ�ռ�ı�־����
	static String selectNum ="" ;
	
	public static void main(String[] args){
		//Ĭ�ϵ� ���ӵ�ַ
		String host = "localhost";
		//Ĭ�ϵĶ˿�
		String port = "6379";
		//�����������д����ֵ
		for(int i = 0; i<args.length;i++){
			//��ȡ����ĵ�ַ
			if(args[i].equals("-h")){
				host = args[i+1];
			}
			//��ȡ����Ķ˿�
			if(args[i].equals("-p")){
				port = args[i+1];
			}
		}
		//��������
		jedis = new Jedis(host,Integer.parseInt(port));
		try{
			//�����Ƿ����ӳɹ�
			jedis.ping();
		}catch (Exception e){
			//�������ʧ�����޸ı�־����
			bool = false;
			//������Ӹı������ʾ�ı�־������ֵ
			askingErr= "Could not connect to Redis at"+e.getMessage();
			System.out.println(askingErr);
		}finally{
			//���ò˵��������ӵĵ�ַ�Ͷ˿�
			runMenu(host,port);
		}		
	}
	public static void runMenu(String host,String port){
		while(true){
			if(bool == false){//�ж��Ƿ����ӳɹ�
				System.out.print("not connected"+">");
				input.nextLine();
				//����ʧ�������κ����ݶ���ʾ���Ӵ���
				System.out.println(askingErr);
				//��������ѭ��
				continue;
			}
			//��ʾ��ʾ���
			System.out.print("myredis "+host+":"+port+selectNum+">");
			//ʹ��entering���� ��¼�û������ֵ
			String entering = input.nextLine();
			//����������ݽ��д���ȥ������Ŀո�
			entering = entering.replaceAll(" +", " ").trim();
			if(entering.toLowerCase().startsWith("p")){//�ж��Ƿ�����p��ͷ������
				//���ö�Ӧ�ķ���  initially_P
				initially_P(entering);
			}else if(entering.toLowerCase().startsWith("e")){//�ж��Ƿ�����e��ͷ������
				//���ö�Ӧ�ķ���  initially_E
				initially_E(entering);
			}else if(entering.toLowerCase().startsWith("i")){//�ж��Ƿ�����i��ͷ������
				//���ö�Ӧ�ķ���  initially_I
				initially_I(entering);
			}else if(entering.toLowerCase().startsWith("q")){//�ж��Ƿ�����q��ͷ������
				//���ö�Ӧ�ķ���  initially_Q
				initially_Q(entering);
			}else if(entering.toLowerCase().startsWith("s")){//�ж��Ƿ�����s��ͷ������
				//���ö�Ӧ�ķ���  initially_S
				initially_S(entering);
			}else if(entering.toLowerCase().startsWith("d")){//�ж��Ƿ�����d��ͷ������
				//���ö�Ӧ�ķ���  initially_D
				initially_D(entering);
			}else if(entering.toLowerCase().startsWith("f")){//�ж��Ƿ�����f��ͷ������
				//���ö�Ӧ�ķ���  initially_F
				initially_F(entering);
			}else if(entering.toLowerCase().startsWith("g")){//�ж��Ƿ�����g��ͷ������
				//���ö�Ӧ�ķ���  initially_G
				initially_G(entering);
			}else if(entering.toLowerCase().startsWith("a")){//�ж��Ƿ�����a��ͷ������
				//���ö�Ӧ�ķ���  initially_A
				initially_A(entering);
			}else if(entering.toLowerCase().startsWith("m")){//�ж��Ƿ�����m��ͷ������
				//���ö�Ӧ�ķ���  initially_M
				initially_M(entering);
			}else if(entering.toLowerCase().startsWith("h")){//�ж��Ƿ�����h��ͷ������
				//���ö�Ӧ�ķ���  initially_H
				initially_H(entering);
			}else if(entering.toLowerCase().startsWith("l")){//�ж��Ƿ�����l��ͷ������
				//���ö�Ӧ�ķ���  initially_L
				initially_L(entering);
			}else if(entering.toLowerCase().startsWith("r")){//�ж��Ƿ�����r��ͷ������
				//���ö�Ӧ�ķ���  initially_R
				initially_R(entering);
			}else if(entering.toLowerCase().startsWith("z")){//�ж��Ƿ�����z��ͷ������
				//���ö�Ӧ�ķ���  initially_Z
				initially_Z(entering);   
			}else{
				//���������ʾ������Ϣ
				System.out.println("(error) ERR unknown command '"+entering+"'");
			}
		}
	}
	/**
	 * ��Z��ͷ
	 * @param entering
	 */
	private static void initially_Z(String entering) {
		if(entering.toLowerCase().startsWith("zadd ")){//sorted set ���͵�zadd��ӷ���    zadd key count value
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==4){//�ж�����ĸ�ʽ�Ƿ���ȷ
				double count ;
				try{
					//����Ӧ��ʽ������תΪdouble���Ҳ�׽�Ƿ�����쳣
					count = Double.parseDouble(values[2]);
				}catch (Exception e){
					//���ת�������쳣�����������Ϣ������
					System.out.println("(integer) 0");
					return;
				}
				//
				System.out.println(jedis.zadd(values[1],count,values[3]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'zadd' command");
			}
		}else if(entering.toLowerCase().startsWith("zcard ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==2){
				System.out.println(jedis.zcard(values[1]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'zcard' command");
			}
		}else if(entering.toLowerCase().startsWith("zcount ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==4){
				int start ;
				int end ;
				try{
					start = Integer.parseInt(values[2]);
					end = Integer.parseInt(values[2]);
				}catch (Exception e){
					System.out.println("(error) ERR value is not an integer or out of range");
					return;
				}
				System.out.println(jedis.zcount(values[1],start,end));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'zcount' command");
			}
		}else if(entering.toLowerCase().startsWith("zrange ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==4){
				int start ;
				int end ;
				try{
					start = Integer.parseInt(values[2]);
					end = Integer.parseInt(values[2]);
				}catch (Exception e){
					System.out.println("(error) ERR value is not an integer or out of range");
					return;
				}
				System.out.println(jedis.zrange(values[1],start,end));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'zrange' command");
			}
		}else if(entering.toLowerCase().startsWith("zrevrange ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==4){
				int start ;
				int end ;
				try{
					start = Integer.parseInt(values[2]);
					end = Integer.parseInt(values[2]);
				}catch (Exception e){
					System.out.println("(error) ERR value is not an integer or out of range");
					return;
				}
				System.out.println(jedis.zrevrange(values[1],start,end));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'zrevrange' command");
			}
		}
	}
	/**
	 * ��r��ͷ
	 * @param entering
	 */
	private static void initially_R(String entering) {
		if(entering.toLowerCase().startsWith("rpush ")){
			//�����ƶ���key
			String key =  getStrArr(entering)[1];
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering.replace("rpush "+key+" ", ""));
			if(values.length>=3){
				System.out.println(jedis.rpush(key,values));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'rpush' command");
			}
		}else if(entering.toLowerCase().startsWith("rpop ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==2){
				System.out.println(jedis.rpop(values[1]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'rpop' command");
			}
		}else if(entering.toLowerCase().startsWith("rpoplpush ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==3){
				System.out.println(jedis.rpoplpush(values[1],values[2]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'rpoplpush' command");
			}
		}
	}
	/**
	 * ��L��ͷ
	 * @param entering
	 */
	private static void initially_L(String entering) {
		if(entering.toLowerCase().startsWith("lpush ")){
			//����ָ����key
			String key =  getStrArr(entering)[1];
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering.replace("lpush "+key+" ", ""));
			if(values.length>=3){
				System.out.println(jedis.lpush(key,values));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'lpush' command");
			}
		}else if(entering.toLowerCase().startsWith("lrange ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==4){
				int start ;
				int end ;
				try{
					start = Integer.parseInt(values[2]);
					end = Integer.parseInt(values[2]);
				}catch (Exception e){
					System.out.println("(error) ERR value is not an integer or out of range");
					return;
				}
				System.out.println(jedis.lrange(values[1],start,end));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'lrange' command");
			}
		}else if(entering.toLowerCase().startsWith("lpop ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==2){
				System.out.println(jedis.lpop(values[1]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'lpop' command");
			}
		}else if(entering.toLowerCase().startsWith("llen ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==2){
				System.out.println(jedis.llen(values[1]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'llen' command");
			}
		}else if(entering.toLowerCase().startsWith("lrem ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==4){
				long count ;
				try{
					count = Integer.parseInt(values[2]);
				}catch (Exception e){
					System.out.println("(integer) 0");
					return;
				}
				System.out.println(jedis.lrem(values[1],count,values[3]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'lrem' command");
			}
		}else if(entering.toLowerCase().startsWith("lset ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==4){
				long index ;
				try{
					index = Integer.parseInt(values[2]);
				}catch (Exception e){
					System.out.println("(integer) 0");
					return;
				}
				System.out.println(jedis.lset(values[1],index,values[3]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'lrem' command");
			}
		}else if(entering.toLowerCase().startsWith("lindex ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==3){
				long index ;
				try{
					index = Integer.parseInt(values[2]);
				}catch (Exception e){
					System.out.println("(nil)");
					return;
				}
				System.out.println(jedis.lindex(values[1],index));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'lindex' command");
			}
		}else if(entering.toLowerCase().startsWith("ltrim ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==4){
				long start;
				long end;
				try{
					start = Integer.parseInt(values[2]);
					end = Integer.parseInt(values[2]);
				}catch (Exception e){
					System.out.println("ok");
					return;
				}
				System.out.println(jedis.ltrim(values[1],start,end));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'lrange' command");
			}
		}
	}
	/**
	 * ��h��ͷ
	 * @param entering
	 */
	private static void initially_H(String entering) {
		if(entering.toLowerCase().startsWith("hset ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==4){
				
				System.out.println(jedis.hset(values[1],values[2],values[3]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'hset' command");
			}
		}else if(entering.toLowerCase().startsWith("hget ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==3){
				System.out.println(jedis.hget(values[1],values[2]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'hget' command");
			}
		}else if(entering.toLowerCase().startsWith("hexists ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==3){
				System.out.println(jedis.hexists(values[1],values[2]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'hexists' command");
			}
		}else if(entering.toLowerCase().startsWith("hlen ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==2){
				System.out.println(jedis.hlen(values[1]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'hlen' command");
			}
		}else if(entering.toLowerCase().startsWith("hdel ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String key =  getStrArr(entering)[1];
			String[] values =  getStrArr(entering.replace("hdel "+key+" ", ""));
			if(values.length>=1){
				System.out.println(jedis.hdel(key,values));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'hdel' command");
			}
		}else if(entering.toLowerCase().startsWith("hgetall ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==2){
				System.out.println(jedis.hgetAll(values[1]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'getall' command");
			}
		}else if(entering.toLowerCase().startsWith("hmset ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length>=3){
				Map<String,String> value = new HashMap<>();
				for(int i = 2 ;i<values.length;i+=2){
					value.put(values[i], values[i+1]);
				}
				System.out.println(jedis.hmset(values[1],value));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'hmset' command");
			}
		}else if(entering.toLowerCase().startsWith("hmget ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String key =  getStrArr(entering)[1];
			String[] values =  getStrArr(entering.replace("hmget "+key+" ", ""));
			if(values.length>=3){
				System.out.println(jedis.hmget(key,values));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'hmget' command");
			}
		}else if(entering.toLowerCase().startsWith("hsetnx ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==4){
				System.out.println(jedis.hsetnx(values[1],values[2],values[3]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'hsetnx' command");
			}
		}else if(entering.toLowerCase().startsWith("hkeys ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==2){
				System.out.println(jedis.hkeys(values[1]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'hkeys' command");
			}
		}else if(entering.toLowerCase().startsWith("hvals ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==2){
				System.out.println(jedis.hvals(values[1]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'hvals' command");
			}
		}
	}
	/**
	 * ��m��ͷ
	 * @param entering
	 */
	private static void initially_M(String entering) {
		if(entering.toLowerCase().startsWith("mset ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering.replace("mset ", ""));
			if(values.length>=2){
				System.out.println(jedis.mset(values));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'mset' command");
			}
		}else if(entering.toLowerCase().startsWith("mget ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering.replace("mget ", ""));
			if(values.length>=1){
				System.out.println(jedis.mget(values));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'mget' command");
			}
		}else if(entering.toLowerCase().startsWith("msetnx ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering.replace("msetnx ", ""));
			if(values.length>=2){
				System.out.println(jedis.msetnx(values));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'msetnx' command");
			}
		}
	}
	/**
	 * ��a��ͷ
	 * @param entering
	 */
	private static void initially_A(String entering) {
		
		if(entering.toLowerCase().startsWith("append ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==3){
				System.out.println(jedis.append(values[1], values[2]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'append' command");
			}
		}
		
	}
	/**
	 *��G��ͷ
	 * @param entering
	 */
	private static void initially_G(String entering) {
		
		if(entering.toLowerCase().startsWith("get ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==2){
				System.out.println(jedis.get(values[1]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'get' command");
			}
		}else if(entering.toLowerCase().startsWith("getset ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==3){
				System.out.println(jedis.getSet(values[1],values[2]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'getset' command");
			}
		}else if(entering.toLowerCase().startsWith("getrange ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==4){
				int startOffset ;
				int endOffset ;
				try{
					startOffset = Integer.parseInt(values[2]);
					endOffset = Integer.parseInt(values[2]);
				}catch (Exception e){
					System.out.println("(error) ERR value is not an integer or out of range");
					return;
				}
				System.out.println(jedis.getrange(values[1],startOffset,endOffset));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'getrange' command");
			}
		}
	}
	/**
	 * ��F��ͷ
	 * @param entering
	 */
	private static void initially_F(String entering) {
		if(entering.trim().toLowerCase().equals("flushdb")){
			System.out.println(jedis.flushDB());
		}else if(entering.trim().toLowerCase().equals("flushall")){
			System.out.println(jedis.flushAll());
		}
	}
	/**
	 * ��D��ͷ
	 * @param entering
	 */
	private static void initially_D(String entering) {
		if(entering.trim().toLowerCase().equals("dbsize")){
			System.out.println(jedis.dbSize());
		}else if(entering.trim().toLowerCase().startsWith("decr ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==2){
				System.out.println(jedis.decr(values[1]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'decr' command");
			}
		}else if(entering.trim().toLowerCase().startsWith("decrby ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==3){
				long integer ;
				try{
					integer = Long.parseLong(values[2]);	
				}catch (Exception e){
					System.out.println("(error) ERR value is not an integer or out of range ");
					return;
				}
				System.out.println(jedis.decrBy(values[1],integer));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'decrby' command");
			}
		}
	}
	/**
	 * ��S��ͷ
	 * @param entering
	 */
	private static void initially_S(String entering) {
		//select
		if(entering.toLowerCase().equals("save")){
			System.out.println(jedis.save());
		}else if(entering.toLowerCase().startsWith("subscribe ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering.replace("subscribe ", ""));
			if(values.length>=1){
				jedis.subscribe(new JedisPubSub() {
					@Override
					public void onMessage(String channel, String message) {
						System.out.println(message);
						super.onMessage(channel, message);
					}
				},values);
			}else{
				System.out.println("Reading messages... (press Ctrl-C to quit)\r\n(error) ERR wrong number of arguments for 'select' command");
			}
		}else if(entering.toLowerCase().startsWith("select ")){
			
			if(entering.split(" ")[1]==null){
				System.out.println("(error) ERR wrong number of arguments for 'select' command");
				return;
			}
			int num ;
			try{
				num = Integer.parseInt(entering.split(" ")[1]);
			}catch (Exception e){
				System.out.println("ok");
				return;
			}
			if(num==0){
				selectNum = "";
				return;
			}
			selectNum = "["+num+"]";
			System.out.println(jedis.select(num));
		}else if(entering.toLowerCase().startsWith("set ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==3){
				System.out.println(jedis.set(values[1], values[2]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'set' command");
			}
		}else if(entering.toLowerCase().startsWith("setex ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==4){
				int seconds ;
				try{
					seconds = Integer.parseInt(values[2]);
				}catch (Exception e){
					System.out.println("(error) ERR value is not an integer or out of range");
					return;
				}
				System.out.println(jedis.setex(values[1],seconds,values[3]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'setex' command");
			}
		}else if(entering.toLowerCase().startsWith("setnx ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==3){
				System.out.println(jedis.setnx(values[1], values[2]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'setnx' command");
			}
		}else if(entering.toLowerCase().startsWith("strlen ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==2){
				System.out.println(jedis.strlen(values[1]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'strlen' command");
			}
		}else if(entering.toLowerCase().startsWith("setrange ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==4){
				int offset ;
				try{
					offset = Integer.parseInt(values[2]);
				}catch (Exception e){
					System.out.println("(error) ERR value is not an integer or out of range");
					return;
				}
				System.out.println(jedis.setrange(values[1],offset,values[3]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'setrange' command");
			}
		}else if(entering.toLowerCase().startsWith("sadd ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String key =  getStrArr(entering)[1];
			String[] values =  getStrArr(entering.replace("sadd "+key+" ", ""));
			if(values.length>=3){
				System.out.println(jedis.sadd(key,values));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'sadd' command");
			}
		}else if(entering.toLowerCase().startsWith("smembers ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==2){
				System.out.println(jedis.smembers(values[1]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'smembers' command");
			}
		}else if(entering.toLowerCase().startsWith("scard ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==2){
				System.out.println(jedis.scard(values[1]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'scard' command");
			}
		}else if(entering.toLowerCase().startsWith("srem ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String key =  getStrArr(entering)[1];
			String[] values =  getStrArr(entering.replace("srem "+key+" ", ""));
			if(values.length>=3){
				System.out.println(jedis.srem(key,values));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'srem' command");
			}
		}else if(entering.toLowerCase().startsWith("sismember ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==3){
				System.out.println(jedis.sismember(values[1], values[2]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'sismember' command");
			}
		}else if(entering.toLowerCase().startsWith("spop ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==2){
				System.out.println(jedis.spop(values[1]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'spop' command");
			}
		}
	}
	/**
	 * ��Q��ͷ
	 * @param entering
	 */
	private static void initially_Q(String entering) {
		if(entering.trim().toLowerCase().equals("quit")){
			System.exit(0);
		}
	}
	/**
	 * ��I��ͷ
	 * @param entering
	 */
	private static void initially_I(String entering) {
		if(entering.trim().toLowerCase().equals("info")){
			System.out.println(jedis.info());
		}else if(entering.trim().toLowerCase().startsWith("incr ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==2){
				System.out.println(jedis.incr(values[1]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'incr' command");
			}
		}else if(entering.trim().toLowerCase().startsWith("incrby ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==3){
				long integer ;
				try{
					integer = Long.parseLong(values[2]);	
				}catch (Exception e){
					System.out.println("(error) ERR value is not an integer or out of range ");
					return;
				}
				System.out.println(jedis.incrBy(values[1],integer));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'incrby' command");
			}
		}
	}
	
	/**
	 * ��p��ͷ������
	 */
	public static void initially_P(String entering){
		if(entering.toLowerCase().equals("ping")){
			System.out.println(jedis.ping());
		}else if(entering.toLowerCase().startsWith("publish ")){
			//����������ݴ���getStrArr(entering)�����н��д���󷵻س�һ������
			String[] values =  getStrArr(entering);
			if(values.length==3){
				System.out.println(jedis.publish(values[1], values[2]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'publish' command");
			}
		}
	}
	/**
	 * ��e��ͷ������
	 */
	public static void initially_E(String entering){
		if(entering.toLowerCase().startsWith("echo ")){
			System.out.println(jedis.echo(entering.split(" ")[1]));
		}
	}
	/**
	 * ���ؽ�ȡ����ַ�����
	 * @param str
	 * @return
	 */
	public static String[] getStrArr(String str){
		String[] arr = str.split(" ");
		return arr;
	}
}
