package com.otdot.hgm;


import com.otdot.hgm.controllers.StopController;
import com.otdot.hgm.dtos.StopResponse;
import com.otdot.hgm.dtos.StopsResponse;
import com.otdot.hgm.queries.Queries;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class HgmApplication {

	public static void main(String[] args) {
		SpringApplication.run(HgmApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/stops").allowedOrigins("http://localhost:19006");
			}
		};
	}

//	@Bean
//	CommandLineRunner runner(){
//
//		return args -> {
//			StopController stopController = new StopController();
//			StopsResponse object = stopController.stopQuery(Queries.STOPSQUERY);
//
//			System.out.println(object);
//		};
//	}
}
