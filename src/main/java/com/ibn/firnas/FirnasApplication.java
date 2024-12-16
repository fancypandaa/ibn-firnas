package com.ibn.firnas;

import com.ibn.firnas.configuration.RsaKeyConfigProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@EnableConfigurationProperties(RsaKeyConfigProperties.class)
public class FirnasApplication {
	private static final Logger log = LoggerFactory.getLogger(FirnasApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(FirnasApplication.class, args);
	}



}
