package fr.eql.projet01.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ManyToAny;

import lombok.Data;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor 
public class Publication implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titre;
	private String texte;
	private Date dateDebut;
	
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
