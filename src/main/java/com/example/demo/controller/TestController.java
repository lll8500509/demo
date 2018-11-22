package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Config.TestConfig;
import com.example.demo.model.Author;
import com.example.demo.service.AuthorService;

@RestController
public class TestController {
	
	@Autowired
	TestConfig testConfig;
	
	/*@Autowired
	Author author;*/
	
	@Autowired
	AuthorService authorService;
	
	@RequestMapping("/")
	public String index() {
		String name = testConfig.getName();
		int age = testConfig.getAge();
		String sex = testConfig.getSex();
		return "name:"+name+",age:"+age+",sex:"+sex;
	}
	
	@RequestMapping("/add")
	public String add() {
		Author author = new Author();
		author.setAge(1312);
		author.setName("kkk");
		author.setSex("boy");
		authorService.add(author);
		return "添加成功！";
	}
	
	@RequestMapping("/get")
	public String get(int id) {
		Author author = authorService.get(id);
		return "name:"+author.name+",age:"+author.age+",sex:"+author.sex;
	}
	
	@RequestMapping("/delete")
	public String delete(int id) {
		authorService.delete(id);
		return "删除成功！";
	}
	
	@RequestMapping("/update")
	public String delete(Author author) {
		authorService.update(author);
		return "更新成功！";
	}
}
