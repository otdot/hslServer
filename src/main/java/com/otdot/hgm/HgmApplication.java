package com.otdot.hgm;


import com.otdot.hgm.controllers.StopController;
import com.otdot.hgm.dtos.StopResponse;
import com.otdot.hgm.queries.Queries;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HgmApplication {

	public static void main(String[] args) {
		SpringApplication.run(HgmApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(){

		return args -> {
			StopController stopController = new StopController();
			StopResponse object = stopController.stopQuery(Queries.STOPQUERY("HSL:2112402"));

			System.out.println(object);
		};
	}
}
