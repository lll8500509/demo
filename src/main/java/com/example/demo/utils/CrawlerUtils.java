package com.example.demo.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrawlerUtils {
	private static Logger LOGGER = LoggerFactory.getLogger(CrawlerUtils.class);
	
	public static List<String>  getText(String url) {
		Document  document;
		try {
			 document = Jsoup.connect(url)
					.timeout(4000)
					.ignoreContentType(true)
					.userAgent("Mozilla\" to \"Mozilla/5.0 (Windows NT 10.0; WOW64; rv:50.0)")
					.get();
		} catch (IOException e) {
			throw new RuntimeException("爬虫出现问题："+e);
		}
		LOGGER.info(document.html());
		
		List<String> urlList = new ArrayList<String>();
		Elements urlNode = document.select("a[href$=.html]");
		for(Element e : urlNode) {
			urlList.add(e.attr("abs:href"));
		}
		LOGGER.info(urlList.toString());
		return urlList;
	}
	
	public static void main(String[] args) {
		getText("http://www.biquge.com.tw/0_66/8024976.html");
		//getText("http://www.biquge.com.tw/0_66/");
	}
}
