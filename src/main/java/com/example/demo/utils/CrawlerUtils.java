package com.example.demo.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.model.tieba.Author;
import com.example.demo.model.tieba.Post;

public class CrawlerUtils {
	
	private static Logger LOGGER = LoggerFactory.getLogger(CrawlerUtils.class);
	
	
	public static List<Post>  getListPost(String url) {
		Document  document;
		List<Post> list = new ArrayList<>();
		try {
			 document = Jsoup.connect(url)
					.timeout(4000)
					.ignoreContentType(true)
					.userAgent("Mozilla\" to \"Mozilla/5.0 (Windows NT 10.0; WOW64; rv:50.0)")
					.get();
			 LOGGER.info("爬取"+url+"成功");
		} catch (IOException e) {
			throw new RuntimeException("爬虫出现问题："+e);
		}
		
		Elements elements = document.select("li.j_thread_list > div:nth-child(1) ");
		for(Element e : elements) {
			Elements element = e.select("div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)");
			if(element==null || element.isEmpty()) {
				continue;
			}
			String TBurl = element.attr("abs:href");
			String title = element.text();
			String author = e.select("div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > span:nth-child(1)").attr("title");
			String authorUrl = e.select("div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > span:nth-child(1) > .frs-author-name-wrap > a:nth-child(1)").attr("abs:href");
			String replyNum = e.select("div:nth-child(1) > span:nth-child(1)").text();
			String lastReplyTime = e.select("div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > span:nth-child(2)").text();
			Post post = new Post();
			post.setTitle(filterEmoji(title));
			post.setUrl(TBurl);
			post.setType(1);
			post.setAddTime(new Date());
			post.setReplyNum(Long.valueOf(replyNum));
			post.setAuthor(filterEmoji(author));
			post.setAuthorUrl(authorUrl);
			post.setAuthorUrlMd5(DigestUtils.md5Hex(authorUrl));
			post.setLastReplyTime(lastReplyTime);
			list.add(post);
			/*LOGGER.info("标题:"+title);
			LOGGER.info("url:"+TBurl);
			LOGGER.info(""+author);
			LOGGER.info("作者URL地址:"+authorUrl);
			LOGGER.info("回复数量:"+replyNum);
			LOGGER.info("最后回帖时间:"+lastReplyTime);
			LOGGER.info("**************************8");*/
		}
		return list;
	}
	
	public static Author getAuthorInfoByUrl(String url) {
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
		Author author = new Author();
		String userName = document.select(".userinfo_username").text();
		String postAge = document.select(".user_name > span:nth-child(2)").text();
		String postNum = document.select(".user_name > span:nth-child(4)").text();
		author.setAuthorUrl(url);
		author.setAuthorUrlMd5(DigestUtils.md5Hex(url));
		author.setName(userName);
		author.setPostAge(Double.parseDouble(getNumber(postAge)));
		author.setPostNum(Double.parseDouble(getNumber(postNum)));
		return author;
	}
	
	  /**
	   * 将emoji表情替换成空串
	  *  
	  * @param source
	  * @return 过滤后的字符串
	  */ 
	 public static String filterEmoji(String source) { 
	  if (source != null && source.length() > 0) { 
	   return source.replaceAll("[\ud800\udc00-\udbff\udfff\ud800-\udfff]", ""); 
	  } else { 
	   return source; 
	  } 
	 }
	 
	 /**
	   * 将字符串中的数字提取出来
	  *  
	  * @param str
	  * @return 提取后的数字
	  */ 
	 public static String getNumber(String str) {
		 Pattern p  =  Pattern.compile("\\d+\\.?\\d*");
		 Matcher m = p.matcher(str);
		 if(m.find()) {
			 if(m.group()!=null) {
				 return m.group();
			 }
		 }
		 return null;
	 }
	

	public static void main(String[] args) {
		//getListPost("https://tieba.baidu.com/f?kw=%E7%BD%91%E6%98%93%E9%98%B4%E9%98%B3%E5%B8%88&fr=index&fp=0&ie=utf-8");
		//getAuthorInfoByUrl("https://tieba.baidu.com/home/main/?un=%E8%A1%A3%E4%BB%99%E7%94%B2%E9%87%91&ie=utf-8&id=7b1fe8a1a3e4bb99e794b2e98791342f&fr=frs");
		//getAuthorInfoByUrl("https://tieba.baidu.com/home/main/?un=%E9%83%BDTND%E6%B5%AE%E4%BA%91&ie=utf-8&id=2732e983bd544e44e6b5aee4ba91a11c&fr=frs");
		System.out.println(getNumber("首都航空:3年"));
	}
}
