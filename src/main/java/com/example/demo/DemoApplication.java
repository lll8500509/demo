package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*@MapperScan("com.example.demo.mapper")*/
/*@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)*/
@MapperScan("com.example.demo.mapper")
@SpringBootApplication
@EnableTransactionManagement
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
