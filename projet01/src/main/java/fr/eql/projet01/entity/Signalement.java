package fr.eql.projet01.entity;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor 
public class Signalement implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Temporal(TemporalType.DATE)
	private Date dateSignalement;
	@Temporal(TemporalType.DATE)
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
	
	public void addUtilisateur(Utilisateur u) {
		if (listUtilisateurSignalement == null) {
			listUtilisateurSignalement = new ArrayList<Utilisateur>();
		}
		this.listUtilisateurSignalement.add(u);
	}
}
