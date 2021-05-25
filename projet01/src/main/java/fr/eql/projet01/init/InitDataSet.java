package fr.eql.projet01.init;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("initData") 
@Component
public class InitDataSet {

	@PostConstruct
	public void initJeuxDeDonneesQueJaime() {
		
	}
}
