package com.et.redis;

import java.util.Scanner;

import com.et.redis.RedisBuffer;

public class Main {
	static Scanner input = new Scanner(System.in);
	public static void main(String[] args){
		menu();
	}
	public static void menu(){
		System.out.println("������Ҫ���ҵ��û�����");
		String name=input.next();
		User user = RedisBuffer.get(name);
		if(user == null){
			System.out.println("û�и��û�");
		}else{
			System.out.println(user);
		}
		
	}
}
