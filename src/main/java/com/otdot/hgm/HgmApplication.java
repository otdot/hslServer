package com.otdot.hgm;

import com.otdot.hgm.controllers.TripController;
import com.otdot.hgm.dtos.TripsRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableScheduling
public class HgmApplication {

	public static void main(String[] args) {
		SpringApplication.run(HgmApplication.class, args);
	}

	@Autowired
	TripController tripController;


	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/stops").allowedOrigins("http://localhost:19006");
				registry.addMapping("/graphql").allowedOrigins("http://localhost:19006");
			}
		};
	}

//	@Bean
//	CommandLineRunner runner(){
//		return args -> {
//			TripsRes object = tripController.saveTrips();
//			System.out.println(object);
//		};
//	}
}
