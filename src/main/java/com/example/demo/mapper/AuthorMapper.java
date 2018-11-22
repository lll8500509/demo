package com.example.demo.mapper;

import com.example.demo.model.Author;

public interface AuthorMapper {
    int deleteByPrimaryKey(Long authorId);

    int insert(Author record);

    int insertSelective(Author record);

    Author selectByPrimaryKey(Long authorId);

    int updateByPrimaryKeySelective(Author record);

    int updateByPrimaryKey(Author record);
}