package com.data.examen.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.data.examen.Entities.Banque;
import com.data.examen.Entities.Compte;
import com.data.examen.Repository.BanqueRepository;
import com.data.examen.Repository.CompteRepository;

@Controller
@RequestMapping("comptes")
public class CompteController {

 private final CompteRepository compteRepository;
 private final BanqueRepository banqueRepository;
 //injection des dependences via le constructeur
 public CompteController(BanqueRepository banqueRepository,CompteRepository compteRepository) {
	    this.banqueRepository = banqueRepository;
	    this.compteRepository = compteRepository;
	}

 @GetMapping("listeComptes")
 public String lister(Model model) {
	 model.addAttribute("comptes",compteRepository.findAll());
	 return "listeComptes";
 }
 
 @GetMapping("add")
 public String ajouter(Model model) {
	 model.addAttribute("banques",banqueRepository.findAll());
	 model.addAttribute("compte",new Compte());
	 return "ajoutercompte";
 }
 @PostMapping("add")
 public String add(@RequestParam("banqueId")long id, @ModelAttribute("compte") Compte c) {
	Banque b=banqueRepository.getById(id); 
	c.setBanque(b);
	compteRepository.save(c);
	 
	return "redirect:listeComptes";
	
 }
 @GetMapping("detailsCompte/{id}")
 public String détails(@PathVariable("id") long id, Model model) {
     Compte compte = compteRepository.getById(id); 
     model.addAttribute("compte", compte);
     model.addAttribute("banque", compte.getBanque()); // pour accéder facilement à la banque dans la vue
     return "detailsCompte"; 
 }
 @GetMapping("depot/{id}")
 public String deposer(@PathVariable("id") Long id, Model model) {
     Compte compte = compteRepository.getById(id); // ou findById(id).get()
     model.addAttribute("c", compte);
     return "Depot"; // Vue avec le formulaire pour entrer le montant
 }
 @PostMapping("depot/{id}")
 public String deposer(@PathVariable("id") Long id,
                       @RequestParam("montant") double montant,
                       Model model) {

     Compte compte = compteRepository.getById(id);
     compte.setSolde(compte.getSolde() + montant);
     compteRepository.save(compte); // Sauvegarde en base

     return "redirect:/comptes/detailsCompte"; // Redirection vers la liste
 }
 @GetMapping("retrait/{id}")
 public String retrait(@PathVariable("id") Long id, Model model) {
     Compte compte = compteRepository.getById(id);
     model.addAttribute("c", compte);
     return "retrait"; // Formulaire pour entrer le montant à retirer
 }
 @PostMapping("retrait/{id}")
 public String retirer(@PathVariable("id") Long id,
                       @RequestParam("montant") double montant,
                       Model model) {

     Compte compte = compteRepository.getById(id);
     compte.setSolde(compte.getSolde() - montant);
     compteRepository.save(compte); // Sauvegarde en base

     return "redirect:../comptes/detailsCompte/{id}"; // Redirection vers les détails du compte avec l'ID
 }



 @GetMapping("supprimer/{id}")
 public String supprimer(@PathVariable("id")long id) {
 
	    compteRepository.deleteById(id);
	 
         return "redirect:/comptes/listeComptes"; } 

 }

 


