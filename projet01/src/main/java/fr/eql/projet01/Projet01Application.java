package fr.eql.projet01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Projet01Application extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Projet01Application.class);
		app.setAdditionalProfiles("initData");
		ConfigurableApplicationContext context = app.run(args);
	}
}