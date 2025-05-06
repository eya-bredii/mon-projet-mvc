package com.data.examen.Controllers;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.data.examen.Entities.Banque;
import com.data.examen.Entities.Compte;
import com.data.examen.Repository.BanqueRepository;
import com.data.examen.Repository.CompteRepository;

@Controller
@RequestMapping("comptes")
public class CompteController {
	
 private final CompteRepository compteRepository;
 private final BanqueRepository banqueRepository;
 
 public CompteController(BanqueRepository banqueRepository,CompteRepository compteRepository) {
	    this.banqueRepository = banqueRepository;
	    this.compteRepository = compteRepository;
	}

 @GetMapping("listeComptes")
 public String liste(Model model) {
	 model.addAttribute("comptes",compteRepository.findAll());
	 return "listeComptes";
 }
 @GetMapping("add")
 public String ajouter(Model model) {
	 model.addAttribute("banques",banqueRepository.findAll());
	 model.addAttribute("compte",new Compte());
	 return "ajoutercompte";
 }

 
 @PostMapping("/add")
 public String add(@RequestParam("banqueId") long id,
                   @ModelAttribute("compte") Compte c,
                   @RequestParam("image") MultipartFile imageFile) throws IOException {

     if (!imageFile.isEmpty()) {
         // Nom unique pour éviter les conflits
         String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();

         // Obtenir le chemin vers static/images
         File staticImageFolder = new ClassPathResource("static/images").getFile();
         Path imagePath = Paths.get(staticImageFolder.getAbsolutePath(), fileName);

         // Créer le dossier s’il n’existe pas
         Files.createDirectories(imagePath.getParent());

         // Copier le fichier dans le dossier
         Files.copy(imageFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

         // Enregistrer l’URL de l’image
         c.setImageUrl("/images/" + fileName);
     }

     // Associer la banque
     Banque b = banqueRepository.getById(id);
     c.setBanque(b);

     // Enregistrer le compte
     compteRepository.save(c);

     return "redirect:listeComptes";
 }




 @GetMapping("detailsCompte/{id}")
 public String détails(@PathVariable("id") long id, Model model) {
     Compte compte = compteRepository.getById(id); 
     model.addAttribute("compte", compte);
     model.addAttribute("banque", compte.getBanque()); 
     return "detailsCompte"; 
 }
 @GetMapping("depot/{id}")
 public String deposer(@PathVariable("id") Long id, Model model) {
     Compte compte = compteRepository.getById(id); 
     model.addAttribute("compte", compte);
     return "Depot"; 
 }
 @PostMapping("depot/{id}")
 public String deposer(@PathVariable("id") Long id,
                       @RequestParam("montant") double montant,
                       Model model) {

     Compte compte = compteRepository.getById(id);
     compte.setSolde(compte.getSolde() + montant);
     compteRepository.save(compte); 
    
     return "redirect:/comptes/detailsCompte/{id}";

 }
 @GetMapping("retrait/{id}")
 public String retrait(@PathVariable("id") Long id, Model model) {
     Compte compte = compteRepository.getById(id);
     model.addAttribute("compte", compte);
     return "retrait"; 
 }
 @PostMapping("retrait/{id}")
 public String retirer(@PathVariable("id") Long id,
                       @RequestParam("montant") double montant,
                       Model model) {

     Compte compte = compteRepository.getById(id);
     compte.setSolde(compte.getSolde() - montant);
     compteRepository.save(compte); 
     
     return "redirect:/comptes/detailsCompte/{id}"; 
 }



 @GetMapping("supprimer/{id}")
 public String supprimer(@PathVariable("id")long id) {
 
	    compteRepository.deleteById(id);
	 
         return "redirect:/comptes/listeComptes"; } 
 
 @GetMapping("modifier/{id}")
 public String modifierCompte(@PathVariable("id") long id, Model model) {
     Compte compte = compteRepository.getById(id);
     model.addAttribute("compte", compte);
     model.addAttribute("banques", banqueRepository.findAll());
     return "modifier";
 }
 @PostMapping("modifier")
 public String modifierCompte(@ModelAttribute("compte") Compte compteModifie,
                              @RequestParam("banqueId") Long banqueId,
                              @RequestParam(value = "image", required = false) MultipartFile imageFile) throws IOException {

     Compte compte = compteRepository.getById(compteModifie.getId());

     compte.setTitulaire(compteModifie.getTitulaire());
     compte.setCin(compteModifie.getCin());
     compte.setEmail(compteModifie.getEmail());
     compte.setSolde(compteModifie.getSolde());

     Banque banque = banqueRepository.getById(banqueId);
     compte.setBanque(banque);

     if (imageFile != null && !imageFile.isEmpty()) {
    	    String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
    	    // Création du chemin absolu pour le dossier "uploads"
    	    Path imagePath = Paths.get(System.getProperty("user.dir"), "uploads", fileName);
    	    Files.createDirectories(imagePath.getParent()); // Crée le dossier si nécessaire
    	    Files.copy(imageFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

    	    // Enregistre l'URL relative pour l'affichage
    	    compte.setImageUrl("/uploads/" + fileName);
    	}

     compteRepository.save(compte);

     return "redirect:/comptes/listeComptes";
 }

 }

 


