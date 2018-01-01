package com.et.redismap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



public class ObjectSerialization {
	/**
	 * 将对象序列化为byte数组
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static byte[] objToByte(Object obj) throws Exception{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(obj);
		oos.close();
		return bos.toByteArray();
	}
	/**
	 * 将数组序列号为对象
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static Object byteToObj(byte[] value) throws Exception{
		ByteArrayInputStream bis = new ByteArrayInputStream(value);
		ObjectInputStream ois = new ObjectInputStream(bis);
		return ois.readObject();
	}
}
