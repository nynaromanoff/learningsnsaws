package io.git.learningsnsaws.propostApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PropostAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropostAppApplication.class, args);
	}

}
