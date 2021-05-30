package fr.eql.projet01.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor 
public class Support {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String typeSupport;
	private String chemin;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idPublicationSupport")
	private Publication publicationSupport;
    @Lob // save files to db
    private byte[] image;

	@ManyToOne(fetch = FetchType.LAZY)
	private Annonce annonceSupport;
}