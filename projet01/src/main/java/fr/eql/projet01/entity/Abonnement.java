package fr.eql.projet01.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor 
public class Abonnement implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date dateDébut;
	private Date dateFin;
	
	@ManyToOne
	@JoinColumn(name="follower_id")
	@MapsId("id") 
	private Utilisateur follower;
	
	@ManyToOne
	@JoinColumn(name="following_id")
	@MapsId("id") 
	private Utilisateur following;
	
	
	
}
