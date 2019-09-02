package com.webshop.webshop;

import com.webshop.webshop.run.DatabaseSetup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class WebshopApplication {

	private DatabaseSetup databaseSetup;

	@Autowired
	public WebshopApplication(DatabaseSetup databaseSetup) {
		this.databaseSetup = databaseSetup;
	}

	public static void main(String[] args) {
		SpringApplication.run(WebshopApplication.class, args);

	}
}
