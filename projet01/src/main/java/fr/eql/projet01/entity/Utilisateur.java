package fr.eql.projet01.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
	@NamedQuery(name="Utilisateur.findByProfil", query="SELECT u FROM Utilisateur AS u WHERE u.profile=:profil"),
	@NamedQuery(name="Utilisateur.findAll", query="SELECT u FROM Utilisateur u")})
@Entity
@Getter @Setter @NoArgsConstructor 
public class Utilisateur implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String prenom;
	@Temporal(TemporalType.DATE) // 2021-05-25 <-- en bdd
	private Date dateNais;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Sexe sexe;
	
	@Column(unique = true)
	private String mail;
	private String telephone;
	private String rue;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Ville ville;
	private String complement;
	@Column(unique = true)
	private String profile;
	private String passeWord;
	@Temporal(TemporalType.DATE)
	private Date dateInscription;
	@Temporal(TemporalType.DATE)
	private Date dateResiliation;
		
	@OneToMany(fetch=FetchType.LAZY,mappedBy="utilisateur")
	private List<Publication> listPublication;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="utilisateur")
	private List<Annonce> listeAnnonce;
	
	@ManyToMany(mappedBy = "listUtilisateurSignalement")
	private List<Signalement> signalement;
	
	@OneToMany(mappedBy="follower")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Abonnement> Listefollower;
	
	@OneToMany(mappedBy="following")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Abonnement> Listefollowing; 
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Droits droits;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private MotifResiliation MotifResiliation;
}
