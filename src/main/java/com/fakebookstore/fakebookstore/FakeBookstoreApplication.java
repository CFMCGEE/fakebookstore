package com.fakebookstore.fakebookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//@EnableJpaAuditing
public class FakeBookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(FakeBookstoreApplication.class, args);
	}

}
