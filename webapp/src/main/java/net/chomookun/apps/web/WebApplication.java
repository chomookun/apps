package net.chomookun.apps.web;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(WebApplication.class);
		springApplication.setRegisterShutdownHook(false);
		springApplication.run(args);
	}

	@Bean
	@ConfigurationProperties("spring.datasource.hikari")
	public DataSource dataSource() throws Exception {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}
}
