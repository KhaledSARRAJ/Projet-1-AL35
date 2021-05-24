package fr.eql.projet01.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor 
public class Signalement implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date dateSignalement;
	private Date datetraitement;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private MotifSignalement motifSignalement;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "utilisateur_signalement",
	joinColumns = {@JoinColumn(name = "signalement_id")},
	inverseJoinColumns = {@JoinColumn(name = "utilisateur_id")})
	private List<Utilisateur> listUtilisateurSignalement;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Publication publication;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Annonce annonce;
	
}
