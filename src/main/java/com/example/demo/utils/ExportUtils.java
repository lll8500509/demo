package com.example.demo.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.example.demo.model.novel.Novel;

public class ExportUtils {
	
	public static void exportNovel(List<Novel> list) {
		String path = "C:\\Users\\Administrator\\Desktop\\新建文件夹 (2)\\月厨.txt";
		File f = new File(path);
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fw = new FileWriter(f, true);
			pw =  new PrintWriter(fw);
			String novelTitle = "";
			String novelContent = "";
			for(Novel novel : list) {
				novelTitle = novel.getTitle();
				novelContent = novel.getContent();
				String title = "                  "+novelTitle;
				String content = novelContent.replaceAll("\n", "\r\n");
			//	System.out.println(novelContent);
				System.out.println(content.length());
				pw.println();
				pw.println();
				pw.println(title);
				pw.println();
				pw.println(content);
				pw.flush();
				pw.println();
				pw.println();
				pw.println("--------------本章结束-----------");
				pw.flush();
				System.out.println(novelTitle+"插入成功！");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			pw.close();
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		//exportNovel();
	}
}
