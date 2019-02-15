package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Config.TestConfig;
import com.example.demo.model.tieba.Author;
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
	
}
