package com.data.examen.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Compte {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String titulaire;
	private double solde;
	@ManyToOne
	private Banque banque;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Banque getBanque() {
		return banque;
	}
	public void setBanque(Banque banque) {
		this.banque = banque;
	}
	
	public String getTitulaire() {
		return titulaire;
	}
	public void setTitulaire(String titulaire) {
		this.titulaire=titulaire;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde=solde;
	}
	public Compte(Long id,String titulaire,double solde) {
		this.id=id;
		this.titulaire=titulaire;
		this.solde=solde;
	}
	public Compte(String titulaire,double solde) {
		
		this.titulaire=titulaire;
		this.solde=solde;
	}
	public Compte() {}
	


}
