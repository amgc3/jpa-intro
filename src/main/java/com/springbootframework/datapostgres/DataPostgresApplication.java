package com.springbootframework.datapostgres;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DataPostgresApplication {

	// configure a ModelMapper class as Bean so that it can be injected in the controller class
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(DataPostgresApplication.class, args);
	}

}
