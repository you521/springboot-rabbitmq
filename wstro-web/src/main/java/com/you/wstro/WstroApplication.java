package com.you.wstro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
// 扫描mapper包
@MapperScan("com.you.wstro.mapper")
public class WstroApplication {

	public static void main(String[] args) {
		SpringApplication.run(WstroApplication.class, args);
	}

}
