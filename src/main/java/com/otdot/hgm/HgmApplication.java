package com.otdot.hgm;


import com.apollographql.apollo.ApolloClient;
import com.otdot.hgm.controllers.StopController;
import com.otdot.hgm.dtos.StopResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HgmApplication {

	@Autowired
	OkClient okClient;

	public static void main(String[] args) {
		SpringApplication.run(HgmApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(){

		return args -> {
			StopController stopController = new StopController();
			StopResponse object = stopController.getStops();
			System.out.println(object);
		};
	}
}
