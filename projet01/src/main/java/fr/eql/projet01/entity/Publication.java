package fr.eql.projet01.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor 
public class Publication implements Serializable{

	@Override
	public String toString() {
		return "Publication [id=" + id + ", titre=" + titre + ", texte=" + texte + ", dateDebut=" + dateDebut
				+ ", utilisateur=" + utilisateur + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titre;
	private String texte;
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	
	@OneToMany(mappedBy = "publicationSupport", cascade = CascadeType.ALL)
	private List<Support> support;
		
	@ManyToMany(mappedBy = "listPublicationTheme", cascade = CascadeType.ALL)
	private List<Theme> theme;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Utilisateur utilisateur;	
	
	public Publication(String titre, String texte) {
		super();
		this.titre = titre;
		this.texte = texte;
	}
	
}