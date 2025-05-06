package com.data.examen.Controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.data.examen.Entities.Banque;
import com.data.examen.Repository.BanqueRepository;


@Controller
@RequestMapping("banques")
public class BanqueController {

 private final BanqueRepository banqueRepository;
		//@Autorized
public BanqueController(BanqueRepository banqueRepository) {
	this.banqueRepository=banqueRepository;
		   
		}
@GetMapping("list")
public String afficher(Model model) {
		
List<Banque>banques=(List<Banque>) banqueRepository.findAll();
			model.addAttribute("banques",banques);

			return "afficherBanque";
		}
@GetMapping ("add")
public String ajouter(Model model){
		
			model.addAttribute("banque",new Banque());
			return "addbanque";
		}
@PostMapping("add")
public String add(@ModelAttribute Banque banque)
		{
			banqueRepository.save(banque);
			return "redirect:list";
			
			
		}
@GetMapping("/supprimer/{id}")
public String supprimer(@PathVariable Long id) {
    banqueRepository.deleteById(id);
    return "redirect:../list"; 
}

}
