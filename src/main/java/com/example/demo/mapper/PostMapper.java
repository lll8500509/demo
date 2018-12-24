package com.example.demo.mapper;

import java.util.List;

import com.example.demo.model.Post;

public interface PostMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);
    
    int insertListPost(List<Post> list);
    
    List<Post> getListPost();
}