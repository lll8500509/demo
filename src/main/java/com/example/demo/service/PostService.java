package com.example.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.PostMapper;
import com.example.demo.model.tieba.Post;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class PostService {
	private static Logger LOGGER = LoggerFactory.getLogger(PostService.class);
	
	private final static String SUCCESS = "success";
	
	private final static String FAILED = "failed";
	
	@Autowired
	PostMapper postMapper;
	
	public String insertPost(Post post) {
		try {
			postMapper.insert(post);
		} catch (Exception e) {
			LOGGER.info("插入帖子失败",e);
			return FAILED;
		}
		LOGGER.info("插入帖子成功");
		return SUCCESS;
	}
	
	public String insertListPost(List<Post> list) {
		try {
			postMapper.insertListPost(list);
		} catch (Exception e) {
			LOGGER.info("插入list帖子失败",e);
			return FAILED;
		}
		LOGGER.info("插入list帖子成功");
		return SUCCESS;
	}
	
	public List<Post> getListPost(int pn){
		PageHelper.startPage(pn, 10);
		PageInfo<Post> page = new PageInfo<Post>(postMapper.getListPost());
		return page.getList();
	}
}
