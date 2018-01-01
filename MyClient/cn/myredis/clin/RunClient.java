package cn.myredis.clin;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class RunClient {
	//scanner 输入流
	static Scanner input = new Scanner(System.in);
	//标志变量判断是否连接成功
	static boolean bool = true;
	//登录错误的提示消息
	static String askingErr;
	//链接数据库
	static Jedis jedis; 
	//切换数据库空间时，为用户显示空间的标志变量
	static String selectNum ="" ;
	
	public static void main(String[] args){
		//默认的 连接地址
		String host = "localhost";
		//默认的端口
		String port = "6379";
		//遍历主方法中传入的值
		for(int i = 0; i<args.length;i++){
			//获取输入的地址
			if(args[i].equals("-h")){
				host = args[i+1];
			}
			//获取输入的端口
			if(args[i].equals("-p")){
				port = args[i+1];
			}
		}
		//创建链接
		jedis = new Jedis(host,Integer.parseInt(port));
		try{
			//测试是否链接成功
			jedis.ping();
		}catch (Exception e){
			//如果连接失败则修改标志变量
			bool = false;
			//如果连接改变错误提示的标志变量的值
			askingErr= "Could not connect to Redis at"+e.getMessage();
			System.out.println(askingErr);
		}finally{
			//调用菜单传入连接的地址和端口
			runMenu(host,port);
		}		
	}
	public static void runMenu(String host,String port){
		while(true){
			if(bool == false){//判断是否链接成功
				System.out.print("not connected"+">");
				input.nextLine();
				//连接失败输入任何内容都提示连接错误
				System.out.println(askingErr);
				//跳过本次循环
				continue;
			}
			//显示提示语句
			System.out.print("myredis "+host+":"+port+selectNum+">");
			//使用entering变量 记录用户输入的值
			String entering = input.nextLine();
			//将输入的内容进行处理去掉多余的空格
			entering = entering.replaceAll(" +", " ").trim();
			if(entering.toLowerCase().startsWith("p")){//判断是否是以p开头的命令
				//调用对应的方法  initially_P
				initially_P(entering);
			}else if(entering.toLowerCase().startsWith("e")){//判断是否是以e开头的命令
				//调用对应的方法  initially_E
				initially_E(entering);
			}else if(entering.toLowerCase().startsWith("i")){//判断是否是以i开头的命令
				//调用对应的方法  initially_I
				initially_I(entering);
			}else if(entering.toLowerCase().startsWith("q")){//判断是否是以q开头的命令
				//调用对应的方法  initially_Q
				initially_Q(entering);
			}else if(entering.toLowerCase().startsWith("s")){//判断是否是以s开头的命令
				//调用对应的方法  initially_S
				initially_S(entering);
			}else if(entering.toLowerCase().startsWith("d")){//判断是否是以d开头的命令
				//调用对应的方法  initially_D
				initially_D(entering);
			}else if(entering.toLowerCase().startsWith("f")){//判断是否是以f开头的命令
				//调用对应的方法  initially_F
				initially_F(entering);
			}else if(entering.toLowerCase().startsWith("g")){//判断是否是以g开头的命令
				//调用对应的方法  initially_G
				initially_G(entering);
			}else if(entering.toLowerCase().startsWith("a")){//判断是否是以a开头的命令
				//调用对应的方法  initially_A
				initially_A(entering);
			}else if(entering.toLowerCase().startsWith("m")){//判断是否是以m开头的命令
				//调用对应的方法  initially_M
				initially_M(entering);
			}else if(entering.toLowerCase().startsWith("h")){//判断是否是以h开头的命令
				//调用对应的方法  initially_H
				initially_H(entering);
			}else if(entering.toLowerCase().startsWith("l")){//判断是否是以l开头的命令
				//调用对应的方法  initially_L
				initially_L(entering);
			}else if(entering.toLowerCase().startsWith("r")){//判断是否是以r开头的命令
				//调用对应的方法  initially_R
				initially_R(entering);
			}else if(entering.toLowerCase().startsWith("z")){//判断是否是以z开头的命令
				//调用对应的方法  initially_Z
				initially_Z(entering);   
			}else{
				//如果错误提示错误信息
				System.out.println("(error) ERR unknown command '"+entering+"'");
			}
		}
	}
	/**
	 * 以Z开头
	 * @param entering
	 */
	private static void initially_Z(String entering) {
		if(entering.toLowerCase().startsWith("zadd ")){//sorted set 类型的zadd添加方法    zadd key count value
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering);
			if(values.length==4){//判断输入的格式是否正确
				double count ;
				try{
					//将对应格式的类型转为double并且捕捉是否出现异常
					count = Double.parseDouble(values[2]);
				}catch (Exception e){
					//如果转换出现异常则输出错误信息并返回
					System.out.println("(integer) 0");
					return;
				}
				//
				System.out.println(jedis.zadd(values[1],count,values[3]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'zadd' command");
			}
		}else if(entering.toLowerCase().startsWith("zcard ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering);
			if(values.length==2){
				System.out.println(jedis.zcard(values[1]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'zcard' command");
			}
		}else if(entering.toLowerCase().startsWith("zcount ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
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
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
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
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
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
	 * 以r开头
	 * @param entering
	 */
	private static void initially_R(String entering) {
		if(entering.toLowerCase().startsWith("rpush ")){
			//返回制定的key
			String key =  getStrArr(entering)[1];
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering.replace("rpush "+key+" ", ""));
			if(values.length>=3){
				System.out.println(jedis.rpush(key,values));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'rpush' command");
			}
		}else if(entering.toLowerCase().startsWith("rpop ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering);
			if(values.length==2){
				System.out.println(jedis.rpop(values[1]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'rpop' command");
			}
		}else if(entering.toLowerCase().startsWith("rpoplpush ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering);
			if(values.length==3){
				System.out.println(jedis.rpoplpush(values[1],values[2]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'rpoplpush' command");
			}
		}
	}
	/**
	 * 以L开头
	 * @param entering
	 */
	private static void initially_L(String entering) {
		if(entering.toLowerCase().startsWith("lpush ")){
			//返回指定的key
			String key =  getStrArr(entering)[1];
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering.replace("lpush "+key+" ", ""));
			if(values.length>=3){
				System.out.println(jedis.lpush(key,values));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'lpush' command");
			}
		}else if(entering.toLowerCase().startsWith("lrange ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
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
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering);
			if(values.length==2){
				System.out.println(jedis.lpop(values[1]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'lpop' command");
			}
		}else if(entering.toLowerCase().startsWith("llen ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering);
			if(values.length==2){
				System.out.println(jedis.llen(values[1]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'llen' command");
			}
		}else if(entering.toLowerCase().startsWith("lrem ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
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
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
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
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
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
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
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
	 * 以h开头
	 * @param entering
	 */
	private static void initially_H(String entering) {
		if(entering.toLowerCase().startsWith("hset ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering);
			if(values.length==4){
				
				System.out.println(jedis.hset(values[1],values[2],values[3]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'hset' command");
			}
		}else if(entering.toLowerCase().startsWith("hget ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering);
			if(values.length==3){
				System.out.println(jedis.hget(values[1],values[2]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'hget' command");
			}
		}else if(entering.toLowerCase().startsWith("hexists ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering);
			if(values.length==3){
				System.out.println(jedis.hexists(values[1],values[2]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'hexists' command");
			}
		}else if(entering.toLowerCase().startsWith("hlen ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering);
			if(values.length==2){
				System.out.println(jedis.hlen(values[1]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'hlen' command");
			}
		}else if(entering.toLowerCase().startsWith("hdel ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String key =  getStrArr(entering)[1];
			String[] values =  getStrArr(entering.replace("hdel "+key+" ", ""));
			if(values.length>=1){
				System.out.println(jedis.hdel(key,values));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'hdel' command");
			}
		}else if(entering.toLowerCase().startsWith("hgetall ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering);
			if(values.length==2){
				System.out.println(jedis.hgetAll(values[1]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'getall' command");
			}
		}else if(entering.toLowerCase().startsWith("hmset ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
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
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String key =  getStrArr(entering)[1];
			String[] values =  getStrArr(entering.replace("hmget "+key+" ", ""));
			if(values.length>=3){
				System.out.println(jedis.hmget(key,values));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'hmget' command");
			}
		}else if(entering.toLowerCase().startsWith("hsetnx ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering);
			if(values.length==4){
				System.out.println(jedis.hsetnx(values[1],values[2],values[3]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'hsetnx' command");
			}
		}else if(entering.toLowerCase().startsWith("hkeys ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering);
			if(values.length==2){
				System.out.println(jedis.hkeys(values[1]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'hkeys' command");
			}
		}else if(entering.toLowerCase().startsWith("hvals ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering);
			if(values.length==2){
				System.out.println(jedis.hvals(values[1]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'hvals' command");
			}
		}
	}
	/**
	 * 以m开头
	 * @param entering
	 */
	private static void initially_M(String entering) {
		if(entering.toLowerCase().startsWith("mset ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering.replace("mset ", ""));
			if(values.length>=2){
				System.out.println(jedis.mset(values));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'mset' command");
			}
		}else if(entering.toLowerCase().startsWith("mget ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering.replace("mget ", ""));
			if(values.length>=1){
				System.out.println(jedis.mget(values));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'mget' command");
			}
		}else if(entering.toLowerCase().startsWith("msetnx ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering.replace("msetnx ", ""));
			if(values.length>=2){
				System.out.println(jedis.msetnx(values));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'msetnx' command");
			}
		}
	}
	/**
	 * 以a开头
	 * @param entering
	 */
	private static void initially_A(String entering) {
		
		if(entering.toLowerCase().startsWith("append ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering);
			if(values.length==3){
				System.out.println(jedis.append(values[1], values[2]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'append' command");
			}
		}
		
	}
	/**
	 *以G开头
	 * @param entering
	 */
	private static void initially_G(String entering) {
		
		if(entering.toLowerCase().startsWith("get ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering);
			if(values.length==2){
				System.out.println(jedis.get(values[1]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'get' command");
			}
		}else if(entering.toLowerCase().startsWith("getset ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering);
			if(values.length==3){
				System.out.println(jedis.getSet(values[1],values[2]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'getset' command");
			}
		}else if(entering.toLowerCase().startsWith("getrange ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
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
	 * 以F开头
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
	 * 以D开头
	 * @param entering
	 */
	private static void initially_D(String entering) {
		if(entering.trim().toLowerCase().equals("dbsize")){
			System.out.println(jedis.dbSize());
		}else if(entering.trim().toLowerCase().startsWith("decr ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering);
			if(values.length==2){
				System.out.println(jedis.decr(values[1]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'decr' command");
			}
		}else if(entering.trim().toLowerCase().startsWith("decrby ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
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
	 * 以S开头
	 * @param entering
	 */
	private static void initially_S(String entering) {
		//select
		if(entering.toLowerCase().equals("save")){
			System.out.println(jedis.save());
		}else if(entering.toLowerCase().startsWith("subscribe ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
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
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering);
			if(values.length==3){
				System.out.println(jedis.set(values[1], values[2]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'set' command");
			}
		}else if(entering.toLowerCase().startsWith("setex ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
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
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering);
			if(values.length==3){
				System.out.println(jedis.setnx(values[1], values[2]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'setnx' command");
			}
		}else if(entering.toLowerCase().startsWith("strlen ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering);
			if(values.length==2){
				System.out.println(jedis.strlen(values[1]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'strlen' command");
			}
		}else if(entering.toLowerCase().startsWith("setrange ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
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
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String key =  getStrArr(entering)[1];
			String[] values =  getStrArr(entering.replace("sadd "+key+" ", ""));
			if(values.length>=3){
				System.out.println(jedis.sadd(key,values));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'sadd' command");
			}
		}else if(entering.toLowerCase().startsWith("smembers ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering);
			if(values.length==2){
				System.out.println(jedis.smembers(values[1]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'smembers' command");
			}
		}else if(entering.toLowerCase().startsWith("scard ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering);
			if(values.length==2){
				System.out.println(jedis.scard(values[1]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'scard' command");
			}
		}else if(entering.toLowerCase().startsWith("srem ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String key =  getStrArr(entering)[1];
			String[] values =  getStrArr(entering.replace("srem "+key+" ", ""));
			if(values.length>=3){
				System.out.println(jedis.srem(key,values));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'srem' command");
			}
		}else if(entering.toLowerCase().startsWith("sismember ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering);
			if(values.length==3){
				System.out.println(jedis.sismember(values[1], values[2]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'sismember' command");
			}
		}else if(entering.toLowerCase().startsWith("spop ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering);
			if(values.length==2){
				System.out.println(jedis.spop(values[1]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'spop' command");
			}
		}
	}
	/**
	 * 以Q开头
	 * @param entering
	 */
	private static void initially_Q(String entering) {
		if(entering.trim().toLowerCase().equals("quit")){
			System.exit(0);
		}
	}
	/**
	 * 以I开头
	 * @param entering
	 */
	private static void initially_I(String entering) {
		if(entering.trim().toLowerCase().equals("info")){
			System.out.println(jedis.info());
		}else if(entering.trim().toLowerCase().startsWith("incr ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering);
			if(values.length==2){
				System.out.println(jedis.incr(values[1]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'incr' command");
			}
		}else if(entering.trim().toLowerCase().startsWith("incrby ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
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
	 * 以p开头的命令
	 */
	public static void initially_P(String entering){
		if(entering.toLowerCase().equals("ping")){
			System.out.println(jedis.ping());
		}else if(entering.toLowerCase().startsWith("publish ")){
			//将传入的数据传入getStrArr(entering)方法中进行处理后返回出一个数组
			String[] values =  getStrArr(entering);
			if(values.length==3){
				System.out.println(jedis.publish(values[1], values[2]));
			}else{
				System.out.println("(error) ERR wrong number of arguments for 'publish' command");
			}
		}
	}
	/**
	 * 以e开头的命令
	 */
	public static void initially_E(String entering){
		if(entering.toLowerCase().startsWith("echo ")){
			System.out.println(jedis.echo(entering.split(" ")[1]));
		}
	}
	/**
	 * 返回截取后的字符数组
	 * @param str
	 * @return
	 */
	public static String[] getStrArr(String str){
		String[] arr = str.split(" ");
		return arr;
	}
}
