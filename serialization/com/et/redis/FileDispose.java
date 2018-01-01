package com.et.redis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileDispose {
	public User  fileInput(String key){
		File file = new File("E:\\userlist.CSV");
		BufferedReader read = null;
		try {
			
			read = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str=null;
		try {
			while((str=read.readLine())!=null){
				User user = returnUser(str);
				if(user.getName().equals(key)){
					return user;
				}	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			read.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public User returnUser(String value){
		value=value.replace("\"", "");
		String[] arr = value.split(",");
		User u =new User(Integer.valueOf(arr[0]),arr[1],arr[2],arr[3]);
		return u;
	}
}
