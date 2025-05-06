package com.data.examen.Entities;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Compte {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String titulaire;
	private double solde;
	private Long cin;
	private String email;
	

	@ManyToOne
	@JoinColumn(name = "banque_id")
	
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
	public Long getCin() {
		return cin;
	}
	public void setCin(Long cin) {
		this.cin = cin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Compte(Long id,String titulaire,double solde,Long cin,String email) {
		this.id=id;
		this.titulaire=titulaire;
		this.solde=solde;
		this.cin=cin;
		this.email=email;
	}
	public Compte(String titulaire,double solde,long cin,String email) {
		
		this.titulaire=titulaire;
		this.solde=solde;
		this.cin=cin;
		this.email=email;
	}
	public Compte() {}
	


}
