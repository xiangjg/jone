package com.web.jone.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.jone.dao")
@EntityScan(basePackages = "com.jone.entity")
public class JpaConfig {
}
