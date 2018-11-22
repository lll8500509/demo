package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.DemoMapper;
import com.example.demo.model.Author;

@Service
public class AuthorService{
	
	@Autowired
	DemoMapper demoMapper;
	
	public int add(Author author) {
		return demoMapper.addAuthor(author);
	}
	
	public int update(Author author) {
		return demoMapper.updateAuthor(author);
	}
	
	public Author get(Integer id) {
		return demoMapper.getAuthorById(id);
	}
	
	public int delete(Integer id) {
		return demoMapper.deleteAuthorById(id);
	}
}
