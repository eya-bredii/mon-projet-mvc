package com.data.examen.Controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.data.examen.Repository.BanqueRepository;
import com.data.examen.Repository.CompteRepository;



@Controller
@RequestMapping("admin")
public class AdminController {
	//injection des independences
	//Autorized 
	 private final BanqueRepository banqueRepository;
	 private final CompteRepository compteRepository;

	@Autowired
	public AdminController(BanqueRepository banqueRepository,CompteRepository compteRepository) {
	    this.banqueRepository = banqueRepository;
	    this.compteRepository = compteRepository;
	}

}