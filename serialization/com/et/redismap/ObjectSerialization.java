package com.et.redismap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



public class ObjectSerialization {
	/**
	 * ���������л�Ϊbyte����
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
	 * ���������к�Ϊ����
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
