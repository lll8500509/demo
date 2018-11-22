package com.example.demo.utils;

import java.io.IOException;
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
		Elements elements = document.select("li.j_thread_list > div:nth-child(1) > div:nth-child(2) > div:nth-child(1)");
		for(Element e : elements) {
			Elements element = e.select(" div:nth-child(1) > a:nth-child(1)");
			String title = element.text();
			String TBurl = element.attr("abs:href");
			String author = e.select("div:nth-child(2) > span:nth-child(1)").attr("title");
			String authorUrl = e.select("div:nth-child(2) > span:nth-child(1) > span:nth-child(2) > a:nth-child(1)").attr("abs:href");
			LOGGER.info("标题:"+title);
			LOGGER.info("url:"+TBurl);
			LOGGER.info(""+author);
			LOGGER.info("作者URL地址:"+authorUrl);
			LOGGER.info("**************************8");
		}
		return null;
	}
	
	public static void getAuthorInfoByUrl(String url) {
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
		Elements userName = document.select(".userinfo_username");
		Elements postAge = document.select(".user_name > span:nth-child(2)");
		Elements postNum = document.select(".user_name > span:nth-child(4)");
		LOGGER.info(userName.text());
		LOGGER.info(postAge.text());
		LOGGER.info(postNum.text());
	}
	
	public static void main(String[] args) {
		//sss
		//getText("https://tieba.baidu.com/f?kw=%E7%BD%91%E6%98%93%E9%98%B4%E9%98%B3%E5%B8%88&fr=index&fp=0&ie=utf-8");
		getAuthorInfoByUrl("http://tieba.baidu.com/home/main/?un=%E5%B0%8F%E6%B0%B4%E6%99%B6%E8%8E%AB%E8%8E%AB&amp;ie=utf-8&amp;id=5169e5b08fe6b0b4e699b6e88eabe88eaba513&amp;fr=frs");
	
	}
}
