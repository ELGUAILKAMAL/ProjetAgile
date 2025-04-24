package com.gestionprojetagile.ProjetAgile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.gestionprojetagile.ProjetAgile")

@EntityScan(basePackages = "com.gestionprojetagile.ProjetAgile.web.enities")
@EnableJpaRepositories(basePackages = "com.gestionprojetagile.ProjetAgile.web.repositories")
public class ProjetAgileApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetAgileApplication.class, args);
	}

}
