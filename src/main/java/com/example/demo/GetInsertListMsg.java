package com.example.demo;

import java.lang.reflect.Field;

import com.example.demo.model.Post;

public class GetInsertListMsg {

	public static void main(String[] args) {
		//打印插入list<T>的sql语句，如果不需要主键，则需要手动删除主键
		//传入实体，数据库对应的表名
		getEntityMsg(new Post(),"post");
	}

	public static <T> void getEntityMsg(T T,String tableName) {
		Class<? extends Object> cz = T.getClass();
		Field[] fs = cz.getDeclaredFields();
		String insert= "";
		String value = "";
		String update="";
		for(Field f : fs) {
			String name = f.getName();
			f.setAccessible(true);
			insert+=","+name;
			value +=",#{item."+name+"}";
			update+= ","+name+"=values("+name+")";
		}
		insert = formatName(insert.substring(1));
		value = value.substring(1);
		update=formatName(update.substring(1));
		String sql="insert into "+tableName+"\r\n  ("+insert+") "+ "\r\nvalues"+
				"\r\n<foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\">\r\n"+
				"  ("+value+")\r\n</foreach> \r\non duplicate key update \r\n"+
				"  "+update;
		System.out.println(sql);
	}
	
	public static String formatName(String name) {
		StringBuilder sb = new StringBuilder(name);
		int j = 0;
		for(int i=1;i<=name.length()-1;i++) {
			char ch = name.charAt(i);
			if(Character.isLetter(ch)&&!Character.isLowerCase(ch)) {
				sb.insert(i+j, "_");
				j++;
			}
		}
		return sb.toString().toLowerCase();
	}
}
