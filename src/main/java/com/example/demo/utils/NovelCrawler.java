package com.example.demo.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class NovelCrawler {
	private static Logger LOGGER = LoggerFactory.getLogger(NovelCrawler.class);
	
	public static Document getDocument(String url) {
		Document  document;
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
		return document;
	}
	
	public static String getNovel(Document document ) {
		document.select("i").remove();
		String title = document.select("#J_BookCnt > div.read-hd > h3").text();
		String nextUrl = document.select("#J_BtnPageNext").attr("abs:href");
		//document.select("#J_BookRead").select("i").remove();
		document.select("#J_BookRead").select("br").remove();
		String content = document.select("#J_BookRead").select("p").remove().html();
		System.out.println(title);
		System.out.println(nextUrl);
		System.out.println(content);
		System.out.println("****");
		return nextUrl;
	}
	
	
	public static String readJSFile() throws Exception{
		StringBuffer script =  new StringBuffer();
		File file = new File("C:\\Users\\Administrator\\Desktop\\jquery.base64.min.js");
		FileReader filereader = new FileReader(file);
		BufferedReader bufferreader = new BufferedReader(filereader);
		String tempString=null;
		while((tempString = bufferreader.readLine()) != null){
			script.append(tempString).append("\n");
		}
		bufferreader.close();
		filereader.close();
		return script.toString();
	}
	
	public static Document getDocumentByHtmlUnit(String url) {
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(true); // 设置支持JavaScript。
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.setJavaScriptTimeout(20000);
		// 去拿网页
		HtmlPage htmlPage = null;
		try {
			htmlPage =webClient.getPage(url);
			//htmlPage =webClient.getPage("https://www.ciweimao.com/chapter/101535411");
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}finally {
			webClient.close();
		}
		Document document = Jsoup.parse(htmlPage.asXml());
		return document;
	}
	
	public static void main(String[] args) {
		Document document = null;
		String nextUrl = null;
		document = getDocumentByHtmlUnit("https://www.ciweimao.com/chapter/101535411");
		nextUrl = getNovel(document);
		/*while(nextUrl!=null) {
			document = getDocumentByHtmlUnit(nextUrl);
			nextUrl =  getNovel(document);
		}*/
	}
	
	
}
