package com.ibn.firnas;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibn.firnas.configuration.RsaKeyConfigProperties;
import com.ibn.firnas.domain.User;
import com.ibn.firnas.dto.googleFlights.AllFlights;
import com.ibn.firnas.repostiories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
@ServletComponentScan
@EnableConfigurationProperties(RsaKeyConfigProperties.class)
public class FirnasApplication {
	private static final Logger log = LoggerFactory.getLogger(FirnasApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(FirnasApplication.class, args);
	}
	/*
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		return builder.build();
	}
	@Bean
	@Profile("!test")
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception{
		return args -> {
//			Object flights = restTemplate
//					.getForObject("https://serpapi.com/search.json?api_key=b7c4853abe70fe0b85fb700b1abcc8a1d00bf0af142bfe0a19c0bb547e7c1c27&engine=google_flights&departure_id=PEK&arrival_id=AUS&outbound_date=2024-07-12&return_date=2024-07-18&currency=USD&hl=en",
//							Object.class);
//			JSONObject jsonObject = parseJSONFile();
			ObjectMapper objectMapper= new ObjectMapper();
			AllFlights flights=objectMapper.readValue(new File("/home/fancypanda/javaoo/firnas/src/main/java/com/ibn/firnas/dto/test.json"), AllFlights.class);
			log.info(Arrays.stream(flights.flights()).collect(Collectors.toList()).toString());
		};
	}

	public static JSONObject parseJSONFile(String filename) throws JSONException, IOException {
		String content = new String(Files.readAllBytes(Paths.get(filename)));
		return new JSONObject(content);
	}
*/


}
