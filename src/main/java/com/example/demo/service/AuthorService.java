package com.example.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.AuthorMapper;
import com.example.demo.model.Author;
import com.example.demo.model.Post;
import com.example.demo.utils.CrawlerUtils;

@Service
public class AuthorService{
	
private static Logger LOGGER = LoggerFactory.getLogger(AuthorService.class);
	
	private final static String SUCCESS = "success";
	
	private final static String FAILED = "failed";
	
	@Autowired
	AuthorMapper authorMapper;
	
	@Autowired
	PostService postService;
	
	public String insertAuthor(Author author) {
		try {
			authorMapper.insert(author);
		} catch (Exception e) {
			LOGGER.info("插入用户信息失败",e);
			return FAILED;
		}
		LOGGER.info("插入用户信息成功");
		return SUCCESS;
	}
	
	public void insertListAuthor(int page) {
		LOGGER.info("*******************************");
		for(int i=page;i<9999;i++) {
			LOGGER.info("第"+i+"次爬取Author信息");
			List<Post> list = postService.getListPost(i);
			if(null !=list && list.size()>0) {
				for(Post post : list) {
					try {
						insertAuthor(CrawlerUtils.getAuthorInfoByUrl(post.getAuthorUrl()));
					} catch (Exception e) {
						LOGGER.error("作者："+post.getAuthor()+",URL:"+post.getAuthorUrl()+"");
						continue;
					}
				}
			}else {
				break;
			}
		}
		LOGGER.info("Author爬取结束");
		LOGGER.info("*******************************");
	}
}
