package com.good.video;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.good.video.driver.db")
public class GoodVideoAutenticacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodVideoAutenticacaoApplication.class, args);
	}

}
