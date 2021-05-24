package fr.eql.projet01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.eql.projet01.dao.UtilisateurRepository;
import fr.eql.projet01.entity.Utilisateur;

@SpringBootApplication
public class Projet01Application implements CommandLineRunner{

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Projet01Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("************");

		
	}

	
	
}
