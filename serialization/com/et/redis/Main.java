package com.et.redis;

import java.util.Scanner;

import com.et.redis.RedisBuffer;

public class Main {
	static Scanner input = new Scanner(System.in);
	public static void main(String[] args){
		menu();
	}
	public static void menu(){
		System.out.println("请输入要查找的用户姓名");
		String name=input.next();
		User user = RedisBuffer.get(name);
		if(user == null){
			System.out.println("没有该用户");
		}else{
			System.out.println(user);
		}
		
	}
}
