package com.web.jone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;

// 开启缓存
@EnableCaching
@SpringBootApplication
@ServletComponentScan
@ImportResource(value = {"classpath:providers.xml"})
public class JoneApplication extends SpringBootServletInitializer {
	private static Logger logger = LoggerFactory.getLogger(JoneApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(JoneApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(JoneApplication.class, args);
		logger.info("服务器启动完成");
	}
}
