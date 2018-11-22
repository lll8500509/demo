package com.example.demo.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.model.Author;

@Mapper
public interface DemoMapper  {
	public Author getAuthorById(@Param("id") Integer id);
	
	public int addAuthor(Author author);
	
	public int updateAuthor(Author author);
	
	public int deleteAuthorById(@Param("id") Integer id);
}
