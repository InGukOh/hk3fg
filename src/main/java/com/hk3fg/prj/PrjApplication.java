package com.hk3fg.prj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.hk3fg.prj")
public class PrjApplication {

	public static void main(String[] args) {

		SpringApplication.run(PrjApplication.class, args);
	}

}
