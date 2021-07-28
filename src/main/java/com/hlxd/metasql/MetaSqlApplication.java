package com.hlxd.metasql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hlxd.metasql.mapper")
public class MetaSqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetaSqlApplication.class, args);
	}

}
