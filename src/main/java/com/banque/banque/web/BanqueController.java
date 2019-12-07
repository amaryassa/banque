package com.banque.banque.web;

import com.banque.banque.entities.Compte;
import com.banque.banque.entities.Operation;
import com.banque.banque.services.IBanqueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BanqueController {
    @Autowired
    private IBanqueService BanqueService;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(value = "/compte")
    public String index() {
        return "compte";
    }
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(value = "/consulterCompte")
    public String consulterCompte(
            Model model, 
            String codeCompte, 
            @RequestParam(name="page", defaultValue = "0") int page,
            @RequestParam(name="size", defaultValue = "5") int size
            
            )  {


        
        
                model.addAttribute("codeCompte", codeCompte);
                model.addAttribute("pageActuelle", page);
        try {
            Compte compte = BanqueService.consulterCompte(codeCompte);
            Page<Operation> pageOperations = BanqueService.listeOperation(codeCompte, page, size);
            model.addAttribute("listeOperations", pageOperations.getContent());
            int[] pages = new int[pageOperations.getTotalPages()];
            model.addAttribute("compte", compte);
            model.addAttribute("pages", pages);
        } catch (Exception e) {
           model.addAttribute("error", e);
        }
        
        return "compte";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/saveOperation")
    public String saveOperation(Model model, String typeOperation,  String codeCompte, double montant, String codeCompte2) {
      try {
        if(typeOperation.equals("versement")){
            BanqueService.verser(codeCompte, montant);

        } else if(typeOperation.equals("retrait")){
            BanqueService.retirer(codeCompte, montant);
            
        }else if(typeOperation.equals("virement")){
            BanqueService.virement(codeCompte, codeCompte2, montant);
        } 
          
      } catch (Exception e) {
        model.addAttribute("error", e);
        return "redirect:/consulterCompte?codeCompte="+codeCompte+"&error="+e.getMessage();
      }
        return "redirect:/consulterCompte?codeCompte="+codeCompte;
    }

}