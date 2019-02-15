package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.novel.Novel;

@Mapper
public interface NovelMapper {
	public int insertNovel(Novel novel);
	
	public List<Novel> getNovel();
}
