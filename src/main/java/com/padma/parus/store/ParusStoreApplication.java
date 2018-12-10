package com.padma.parus.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan( basePackages = {"com.padma.parus.store"} )
public class ParusStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParusStoreApplication.class, args);
	}
}
