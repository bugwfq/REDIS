package com.et.redismap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class FileProcessing {
	/**
	 * 将文件读取到redis中
	 * @param jedis
	 * @param key
	 */
	public void fileIn(Jedis jedis,String key) {
		File file = new File("E:\\userlist.CSV");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String str = null;
			while((str=br.readLine())!=null){
				Map<String,String> map = stringToMap(str);
				byte[] values = ObjectSerialization.objToByte(map); 
				jedis.rpush(key.getBytes(),values);
			}
			br.close();
		} catch (FileNotFoundException e1) {
			System.out.println("文件不存在异常  FileProcessing类fileIn方法");
		} catch(Exception e){
			System.out.println("Io读取异常  FileProcessing类fileIn方法");
		}
	}
	/**
	 * 将字符串转化为map集
	 * @param str
	 * @return
	 */
	public Map<String,String> stringToMap(String str){
		Map<String,String> map = new HashMap<>();
		if (str==null){
			return null;
		}
		String[] arr=str.split(",");
		map.put("id", arr[0]);
		map.put("姓名", arr[1]);
		map.put("性别", arr[2]);
		map.put("备注", arr[3]);
		return map;
	}
	public void fileOut(Jedis jedis,String key) {
		File file = new File("E:\\userlistcopy.CSV");
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			while(true){
				byte[] getValue = jedis.lpop(key.getBytes());
				if(getValue==null){
					break;
				}
				Map<String,String> map = (Map<String, String>) ObjectSerialization.byteToObj(getValue);
				String vale=map.get("id")+","+map.get("姓名")+","+map.get("性别")+","+map.get("备注")+",";
				bw.write(vale);
				bw.newLine();
				bw.flush();
			}
			bw.close();
		} catch(Exception e){
			System.out.println("异常  FileProcessing类fileout方法");
		}
	}
}
