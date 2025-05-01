package com.data.examen.Entities;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
@Entity
public class Banque {
	
	@Id
	@GeneratedValue
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Compte> getComptes() {
		return comptes;
	}
	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}
	public Banque(String name, List<Compte> comptes) {
		super();
		this.name = name;
		this.comptes=comptes;
	}

	private String name;
	@OneToMany
	private List<Compte> comptes;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Banque() {
	    // constructeur vide nécessaire pour Spring et Hibernate
	}

		
	

}
