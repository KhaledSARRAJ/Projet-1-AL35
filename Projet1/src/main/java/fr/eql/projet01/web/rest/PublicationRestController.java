package fr.eql.projet01.web.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.eql.projet01.entity.Publication;
import fr.eql.projet01.entity.Support;
import fr.eql.projet01.entity.Theme;
import fr.eql.projet01.entity.Utilisateur;
import fr.eql.projet01.exception.AecServiceException;
import fr.eql.projet01.exception.IllegalOperationException;
import fr.eql.projet01.exception.NotValidObjectException;
import fr.eql.projet01.exception.ResourceNotFoundException;
import fr.eql.projet01.service.PublicationService;
import fr.eql.projet01.service.SupportService;
import fr.eql.projet01.service.ThemeService;
import fr.eql.projet01.service.UtilisateurService;
import fr.eql.projet01.ui.request.PublicationRequest;
import fr.eql.projet01.ui.response.PublicationResponse;
import fr.eql.projet01.ui.response.SupportResponse;
import fr.eql.projet01.ui.response.ThemeResponse;

@RestController
@RequestMapping(value="/aec-api-rest/publications", headers="Accept=application/json")
public class PublicationRestController {
	@Autowired
	private PublicationService publicationService;
	@Autowired 
	private SupportService supportService;
	@Autowired
	private ThemeService themeService;
	@Autowired
	private UtilisateurService utilisateurService;
	protected final Logger log = LoggerFactory.getLogger(getClass());

	@GetMapping("/allWithInfos")
	public ResponseEntity<?> getAllWithInfos() {
		ArrayList<PublicationResponse> publicationsResponse = new ArrayList<PublicationResponse>();
		try {
			for(Publication p : publicationService.getAll()) {
				PublicationResponse publicationResponse = new PublicationResponse();
				publicationResponse.setId(p.getId());
				publicationResponse.setTitre(p.getTitre());
				publicationResponse.setTexte(p.getTexte());
				publicationResponse.setDateDebut(p.getDateDebut());
				publicationResponse.setUserId(p.getUtilisateur().getId());
				ArrayList<SupportResponse> tab = new ArrayList<SupportResponse>();
				for(Support s : supportService.findSupportByPublication(p)) {
					SupportResponse supportResponse = new SupportResponse();
					supportResponse.setId(s.getId());
					supportResponse.setChemin(s.getChemin());
					supportResponse.setImage(s.getImage());
					supportResponse.setTypeSupport(s.getTypeSupport());
					tab.add(supportResponse);
					publicationResponse.setListeDesSupports(tab);
				}
				ArrayList<ThemeResponse> tab1 = new ArrayList<ThemeResponse>();
				for(Theme t : themeService.findAll()) {
					ThemeResponse themeResponse = new ThemeResponse();
					if(p.getTheme().contains(t)) {
						themeResponse.setId(t.getId());
						themeResponse.setTitre(t.getTitre());
						themeResponse.setDescription(t.getDescription());
						tab1.add(themeResponse);
						publicationResponse.setListeDesThemes(tab1);
					}	
				}
				publicationsResponse.add(publicationResponse);
			}
		} catch(Exception e) {
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(publicationsResponse,HttpStatus.OK);
	}

	@GetMapping("publicationInfos/{id}")
	public ResponseEntity<?> getOneWithInfos(@PathVariable(required = true) Long id) {
		ArrayList<SupportResponse> tab = new ArrayList<SupportResponse>();
		PublicationResponse publicationResponse = new PublicationResponse();
		Publication result;
		try {
			result = publicationService.findById(id);
			publicationResponse.setId(result.getId());
			publicationResponse.setTitre(result.getTitre());
			publicationResponse.setTexte(result.getTexte());
			publicationResponse.setDateDebut(result.getDateDebut());
			publicationResponse.setUserId(result.getUtilisateur().getId());
			for(Support s : supportService.findSupportByPublication(result)) {
				SupportResponse supportResponse = new SupportResponse();
				supportResponse.setId(s.getId());
				supportResponse.setChemin(s.getChemin());
				supportResponse.setImage(s.getImage());
				supportResponse.setTypeSupport(s.getTypeSupport());
				tab.add(supportResponse);
				publicationResponse.setListeDesSupports(tab);
			}

		}catch (ResourceNotFoundException e) {
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(publicationResponse,HttpStatus.OK);
	}

	@GetMapping("/listePublications")
	public List<Publication> getAll() {
		return publicationService.getAll();
	}

	@GetMapping("/titre")
	public Page<Publication> findByTitreIgnoreCaseContains(String mc, Pageable page) {
		return publicationService.findByTitreIgnoreCaseContains(mc, page);
	}

	@GetMapping("/publiByUser")
	public List<Publication> findPublicationByUser(Utilisateur u) {
		return publicationService.findPublicationByUser(u);
	}

	@GetMapping("/publiByUserTICC")
	public Page<Publication> findByUtilisateurAndTitreIgnoreCaseContains(Utilisateur utilisateur, String titre, Pageable pageable){
		return publicationService.findByTitreIgnoreCaseContains(titre, pageable);
	}

	@GetMapping("/mesPublications{userId}")
	public ResponseEntity<?> getMyPublications(@PathVariable(required = true) Long userId) {
		List<Publication> result;
		try {
			result = publicationService.search(userId);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(result,HttpStatus.OK);
	}

	@GetMapping("/publications/{id}")
	public ResponseEntity<?> getOne(@PathVariable(required=true) Long id) {
		Publication result;
		try {
			result = publicationService.findOne(id);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(result,HttpStatus.OK);
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody(required = true) PublicationRequest publicationRequest, @PathVariable(required = true) Long id) {
		Publication saved;
		try {
			Assert.notNull(publicationRequest, "Ne peut pas être null");
			Assert.notNull(publicationRequest.getId(), "L'identifiant ne peut être null");
			Assert.isTrue(id.equals(publicationRequest.getId()),"L'id de l'url ne correspond pas à celui de l'objet envoyé");
			Publication publication = publicationService.findOne(publicationRequest.getId());
			publication.setTitre(publicationRequest.getTitre());
			publication.setTexte(publicationRequest.getTexte());
			publication.setUtilisateur(utilisateurService.findOne(publicationRequest.getUserId()));
			saved = publicationService.save(publication);
		}catch(ResourceNotFoundException e) {
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.NOT_FOUND);
		}catch(NotValidObjectException e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		} catch(Exception e) {
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(saved,HttpStatus.OK);
		}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody(required = true) PublicationRequest publicationRequest){
		Publication p;
		try {
			Assert.isNull(publicationRequest.getId(), "L'identifiant doit être null");
			Assert.notNull(publicationRequest, "Ne peut pas être null.");
			Publication publication = new Publication();
			publication.setTitre(publicationRequest.getTitre());
			publication.setTexte(publicationRequest.getTexte());
			publication.setDateDebut(new Date());
			publication.setUtilisateur(utilisateurService.findOne(publicationRequest.getUserId()));
			p = publicationService.save(publication);
		} catch (NotValidObjectException e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(p, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) throws AecServiceException {
		Map<String,Object> mapRes = new HashMap<>();
		try {
			publicationService.delete(id);
			mapRes.put("message", "Publication bien supprimée pour l'id "+id);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.NOT_FOUND);
		} catch (IllegalOperationException e) {
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.FORBIDDEN);
		} catch(Exception e) {
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<Object>(mapRes,HttpStatus.OK);
	}
	
	}



