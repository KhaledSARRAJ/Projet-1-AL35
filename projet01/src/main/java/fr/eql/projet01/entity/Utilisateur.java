package fr.eql.projet01.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor 
public class Utilisateur implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String prenom;
	private Date dateNais;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Sexe sexe;
	
	@Column(unique = true)
	private String mail;
	private Long telephone;
	private String rue;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Ville ville;
	private String complement;
	@Column(unique = true)
	private String profile;
	private String passeWord;
	private Date dateInscription;
	private Date dateResiliation;
		
	@OneToMany(fetch=FetchType.LAZY,mappedBy="utilisateur")
	private List<Publication> listPublication;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="utilisateur")
	private List<Annonce> listeAnnonce;
	
	@ManyToMany(mappedBy = "listUtilisateurSignalement")
	private List<Signalement> signalement;
	
	@OneToMany(mappedBy="follower")
	private List<Abonnement> Listefollower;
	
	@OneToMany(mappedBy="following")
	private List<Abonnement> Listefollowing;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Droits droits;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private MotifResiliation MotifResiliation;
}
