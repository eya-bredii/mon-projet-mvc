package com.fst.spring2.Controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fst.spring2.SpringdevApplication;
import com.fst.spring2.entities.Compte;


@Controller
@RequestMapping("/compte")
public class BanqueController {
	
@GetMapping("/login")
  public String showLoginPage() {
        return "login"; 
    }

	
@RequestMapping("comptes")
public String afficher(Model model) {
	model.addAttribute("comptes",SpringdevApplication.comptes);
		return "comptes";
	}

@GetMapping("ajouter")
public String add() {
		return "ajouter";
	}
@PostMapping("ajouter")
public String ajouter(@RequestParam("titulaire") String titulaire,@RequestParam("solde") double solde) {
	int id=SpringdevApplication.comptes.size();
	id++;
	Compte c=new Compte(titulaire,solde);
	c.setId(id);
	SpringdevApplication.comptes.add(c);
		return "redirect:comptes";
	}
@GetMapping("details/{id}")
public String  détails(@PathVariable("id")int id, Model model) {
	for (Compte c:SpringdevApplication.comptes) {
		if (id==c.getId()) {
		model.addAttribute("c",c);
			}
		}
			return "details";
		
	}
@GetMapping("depot/{id}")
public String deposer(@PathVariable("id") int id, Model model) {
	    for (Compte c : SpringdevApplication.comptes) {
	        if (c.getId() == id) {
	            model.addAttribute("c", c);
	            break;
	        }
	    }
	    return "Depot";
	}
	
@PostMapping("depot/{id}")
public String deposer(@PathVariable("id")int id,@RequestParam("montant") double montant,Model model) {
		
	for (Compte c:SpringdevApplication.comptes) {
		if (id==c.getId()) { 
		c.setSolde(c.getSolde()+montant);
		model.addAttribute("c",c);	
		}	
		}
		return "redirect:/comptes";
	
	}
@GetMapping("retrait/{id}")
public String retrait(@PathVariable("id") int id, Model model) {
	    for (Compte c : SpringdevApplication.comptes) {
	        if (c.getId() == id) {
	            model.addAttribute("c", c);
	            break;
	        }
	    }
	    return "retrait";
	}
@PostMapping("retrait/{id}")
public String retrait(@PathVariable("id") int id,@RequestParam("montant") double montant,Model model) {
	    for (Compte c : SpringdevApplication.comptes) {
	        if (c.getId() == id) {
	            if (montant <= c.getSolde()) {
	                c.setSolde(c.getSolde() - montant);
	                return "redirect:/comptes";}
	            } 
	            else 
	                
	                model.addAttribute("erreur", "Solde insuffisant");
	                model.addAttribute("c", c);
	                return "montantRetirer";
	            
	        }
	    
	    return "redirect:/comptes";
	}
	
}