package com.zr.xuezhu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling // 定时任务
public class XuezhuApplication {

	public static void main(String[] args) {
		SpringApplication.run(XuezhuApplication.class, args);
	}


}
