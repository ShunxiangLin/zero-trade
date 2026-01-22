package com.xiang.zerotrade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author linshunxiang
 */
@SpringBootApplication
@EnableScheduling
public class ZeroTradeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZeroTradeApplication.class, args);
	}

}
