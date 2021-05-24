package fr.eql.projet01.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor 
public class Annonce implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titre;
	private String texte;
	private Double prix;
	private Date dateParution;
	
	@OneToMany
	@JoinColumn(referencedColumnName = "id")
	private List<Support> support;
		
	@ManyToMany(mappedBy = "listPublicationTheme")
	private List<Theme> theme;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Utilisateur utilisateur;	
	
	@OneToMany
	@JoinColumn(referencedColumnName = "id")
	private List<Signalement> signalement;
}
