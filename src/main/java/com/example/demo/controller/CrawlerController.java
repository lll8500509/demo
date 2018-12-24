package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Author;
import com.example.demo.model.Post;
import com.example.demo.service.AuthorService;
import com.example.demo.service.PostService;
import com.example.demo.utils.CrawlerUtils;

@RestController
public class CrawlerController {
	
	@Autowired
	PostService postService;
	
	@Autowired
	AuthorService authorService;
	
	@RequestMapping("/crawlerPost")
	public void crawlerPost() {
		for(int i=0;i<5;i++) {
			//List<Post> list = CrawlerUtils.getListPost("https://tieba.baidu.com/f?kw=%E7%BD%91%E6%98%93%E9%98%B4%E9%98%B3%E5%B8%88&fr=index&fp=0&ie=utf-8");
			List<Post> list = CrawlerUtils.getListPost("https://tieba.baidu.com/f?kw=%E6%9D%8E%E6%AF%85&fr=home");
			postService.insertListPost(list);	
		}
	}
	
	@RequestMapping("/crawlerAuthor")
	public String crawlerAuthor(int page) {
		//Author author= CrawlerUtils.getAuthorInfoByUrl("https://tieba.baidu.com/home/main/?un=H%E6%B5%81%E6%B5%AA%E7%9A%84%E5%B0%8F%E7%8C%AB&ie=utf-8&id=9e4548e6b581e6b5aae79a84e5b08fe78cahttps://tieba.baidu.com/home/main/?un=H%E6%B5%81%E6%B5%AA%E7%9A%84%E5%B0%8F%E7%8C%AB&ie=utf-8&id=9e4548e6b581e6b5aae79a84e5b08fe78cabd887&fr=frsbd887&fr=frs");
		//authorService.insertAuthor(author);
		authorService.insertListAuthor(page);
		return "成功";
	}
}
