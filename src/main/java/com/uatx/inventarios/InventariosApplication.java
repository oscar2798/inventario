package com.uatx.inventarios;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventariosApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(InventariosApplication.class);

	public static void main(String[] args){
		LOGGER.debug("HOla desde el logger");
		SpringApplication.run(InventariosApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
