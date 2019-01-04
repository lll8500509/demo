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
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.util.Cookie;
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
	
	public static void login(String url) {
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(true); // 设置支持JavaScript。
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.setJavaScriptTimeout(20000);
		
		String str = "sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22167e9a68c62d6-0ab3f14cfce8e3-541f3415-1049088-167e9a68c63156%22%2C%22%24device_id%22%3A%22167e9a68c62d6-0ab3f14cfce8e3-541f3415-1049088-167e9a68c63156%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%7D; keep_web_status=1; Hm_lvt_1dbadbc80ffab52435c688db7b756e3a=1545813266,1545881175,1546590803; readPage_visits=1; Hm_lpvt_1dbadbc80ffab52435c688db7b756e3a=1546596952; user_id=3492962; reader_id=3492962; login_token=8bd696979f808889e5b86ee2d1154299; get_task_type_sign=1";
		
		Cookie cookie = new Cookie(".ciweimao.com", "test", str);
		CookieManager cookieManager = new CookieManager();
		cookieManager.addCookie(cookie);
		// 去拿网页
		HtmlPage htmlPage = null;
		try {
			webClient.setCookieManager(cookieManager);
			htmlPage =webClient.getPage(url);
			/*HtmlForm form = (HtmlForm) htmlPage.getElementById("J_LoginForm");
			HtmlTextInput username = form.getInputByName("username");
			HtmlPasswordInput password = form.getInputByName("password");
			username.setValueAttribute("15871700869");  //用户名
	        password.setValueAttribute("cwj158717");  //密码
*/		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}finally {
			webClient.close();
		}
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
