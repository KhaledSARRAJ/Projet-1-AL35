package fr.eql.projet01.entity;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor 
public class Utilisateur implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	
	public Utilisateur(String nom) {
		super();
		this.nom = nom;
	}
	
	
	
}
