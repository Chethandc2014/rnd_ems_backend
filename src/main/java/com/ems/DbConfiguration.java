package com.ems;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring.datasource")
public class DbConfiguration {
	
	private String driverClassName;
	private String username;
	private String password;
	private String url;
	
	@Profile("dev")
	@Bean()
	public String	devDatabaseConnection() {
		System.out.println("DEV DB-->"+username);
		return "db Connection for dev env";
	}
	
	@Profile("test")
	@Bean()
	public  String	testDatabaseConnection() {
		System.out.println("TEST DB-->"+username);
		return "db Connection for test env";

	}
	
	

}
