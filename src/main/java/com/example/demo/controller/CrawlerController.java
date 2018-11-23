package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Post;
import com.example.demo.service.PostService;
import com.example.demo.utils.CrawlerUtils;

@RestController
public class CrawlerController {
	
	@Autowired
	PostService postService;
	
	@RequestMapping("/crawlerPost")
	public void crawlerPost() {
		List<Post> list = CrawlerUtils.getListPost("https://tieba.baidu.com/f?kw=%E7%BD%91%E6%98%93%E9%98%B4%E9%98%B3%E5%B8%88&fr=index&fp=0&ie=utf-8");
		postService.insertListPost(list);
	}
}
