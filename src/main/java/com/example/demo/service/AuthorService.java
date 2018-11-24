package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.AuthorMapper;
import com.example.demo.model.Author;

@Service
public class AuthorService{
	
private static Logger LOGGER = LoggerFactory.getLogger(AuthorService.class);
	
	private final static String SUCCESS = "success";
	
	private final static String FAILED = "failed";
	
	@Autowired
	AuthorMapper authorMapper;
	
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
}
