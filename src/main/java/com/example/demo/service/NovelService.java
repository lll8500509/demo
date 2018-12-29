package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.NovelMapper;
import com.example.demo.model.Novel;
import com.example.demo.utils.ExportUtils;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class NovelService {
	
	@Autowired
	NovelMapper novelMapper;
	
	public  Novel getNovel(Document document ) {
		document.select("i").remove();
		String title = document.select("#J_BookCnt > div.read-hd > h3").text();
		String nextUrl = document.select("#J_BtnPageNext").attr("abs:href");
		//document.select("#J_BookRead").select("i").remove();
		document.select("#J_BookRead").select("br").remove();
		String content = document.select("#J_BookRead").select("p").remove().html();
		Novel novel = new Novel();
		novel.setContent(content);
		novel.setNextUrl(nextUrl);
		novel.setTitle(title);
		
		System.out.println(title);
		System.out.println(nextUrl);
		//System.out.println(content);
		return novel;
	}
	
	public static Document getDocumentByHtmlUnit(String url) {
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(true); // 设置支持JavaScript。
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
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
	
	public void CrawlNovel() {
		String url = "https://www.ciweimao.com/chapter/101535411";
		Document document = null;
		String nextUrl = null;
		Novel novel = null;
		
		document = getDocumentByHtmlUnit(url);
		
		novel = getNovel(document);
		novel.setUrl(url);
		novelMapper.insertNovel(novel);
		
		nextUrl = novel.getNextUrl();
		while(nextUrl!=null) {
			document = getDocumentByHtmlUnit(nextUrl);
			novel = new Novel();
			novel =  getNovel(document);
			novel.setUrl(nextUrl);
			novelMapper.insertNovel(novel);
			nextUrl= novel.getNextUrl();
		}
		System.out.println("结束！");
	}
	
	public List<Novel> getListNovel(int pn){
		PageHelper.startPage(pn, 10);
		PageInfo<Novel> page = new PageInfo<>(novelMapper.getNovel());
		return page.getList();
	}
	
	public void exportNovel() {
		for(int i=1;i<=999;i++) {
			List<Novel> list = getListNovel(i);
			if(list!=null && list.size()>0) {
				ExportUtils.exportNovel(list);
			}else {
				System.out.println("list为空！");
				break;
			}
		}
	}
}
