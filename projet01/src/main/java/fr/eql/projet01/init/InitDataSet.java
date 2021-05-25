package fr.eql.projet01.init;

import java.sql.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import fr.eql.projet01.dao.AbonnementRepository;
import fr.eql.projet01.dao.AnnonceRepository;
import fr.eql.projet01.dao.DroitsRepository;
import fr.eql.projet01.dao.MotifResiliationRepository;
import fr.eql.projet01.dao.MotifSignalementRepository;
import fr.eql.projet01.dao.PublicationRepository;
import fr.eql.projet01.dao.SexeRepository;
import fr.eql.projet01.dao.SignalementRepository;
import fr.eql.projet01.dao.SupportRepository;
import fr.eql.projet01.dao.ThemeRepository;
import fr.eql.projet01.dao.UtilisateurRepository;
import fr.eql.projet01.dao.VilleRepository;
import fr.eql.projet01.entity.Publication;
import fr.eql.projet01.entity.Support;
import fr.eql.projet01.entity.Theme;
import fr.eql.projet01.entity.Utilisateur;
import fr.eql.projet01.entity.Ville;

@Profile("initData") 
@Component
@Transactional
public class InitDataSet {

	@Autowired
	private AbonnementRepository abonnementRepository;
	
	@Autowired
	private AnnonceRepository annonceRepository;
	
	@Autowired
	private DroitsRepository droitsRepository;
	
	@Autowired
	private MotifResiliationRepository motifResiliationRepository;
	
	@Autowired
	private MotifSignalementRepository motifSignalementRepository;
	
	@Autowired
	private PublicationRepository publicationRepository;
	
	@Autowired
	private SexeRepository sexeRepository;
	
	@Autowired
	private SignalementRepository signalementRepository;
	
	@Autowired
	private SupportRepository supportRepository;
	
	@Autowired
	private ThemeRepository themeRepository;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private VilleRepository villeRepository;
	
	@PostConstruct
	public void initJeuxDeDonnees() {
		//mettre les données
		
	}
	
	
	private void insertVille(long id, String codePostal, String localite, String pays) {
		Ville ville = new Ville();
		ville.setId(id);
		ville.setCodePostale(codePostal);
		ville.setLocalite(localite);
		ville.setPays(pays);
		villeRepository.save(ville);
	}
	
	private void insertSupport(long id, String typeSupport, String chemin) {
		Support support = new Support();
		support.setId(id);
		support.setTypeSupport(typeSupport);
		support.setChemin(chemin);
		supportRepository.save(support);
	}
	
	private void insertPublication(long id, String titre, String texte, Date dateDeDebut, Utilisateur utilisateur) {
		Publication publication = new Publication();
		publication.setId(id);
		publication.setTexte(texte);
		publication.setTitre(titre);
		publication.setDateDebut(dateDeDebut);
		publication.setUtilisateur(utilisateur);
		publicationRepository.save(publication);
		}
	
	private void insertTheme(long id, String titre, String description) {
		Theme theme = new Theme();
		theme.setId(id);
		theme.setDescription(description);
		theme.setTitre(titre);
		themeRepository.save(theme);
	}
	
	private void addThemePub(long idPub, long idTheme) {
		Publication pub = publicationRepository.findById(idPub).get();
		Theme th = themeRepository.findById(idTheme).get();
		th.addPublication(pub);
		themeRepository.save(th);
	}

	
	
}
