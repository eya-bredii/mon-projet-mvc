package com.fst.spring2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fst.spring2.entities.Compte;

import java.util.ArrayList;
import java.util.List;
@SpringBootApplication
public class SpringdevApplication {
	public static List <Compte> comptes = new ArrayList<>();
	public static void main(String[] args) {
		SpringApplication.run(SpringdevApplication.class, args);
		Compte c1=new Compte(1,"aziz",5000);
		Compte c2=new Compte(2," Eya",5500);
		Compte c3=new Compte(3,"youssef",2500);
	    
	   
	   comptes.add(c1);
	   comptes.add(c2);
	   comptes.add(c3);
	}

}




	
