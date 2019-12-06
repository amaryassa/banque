package com.banque.banque.web;

import com.banque.banque.entities.Compte;
import com.banque.banque.entities.Operation;
import com.banque.banque.services.IBanqueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BanqueController {
    @Autowired
    private IBanqueService BanqueService;

    
	@GetMapping(value = "/compte")
    public String index() {
        return "compte";
    }

    @GetMapping(value = "/consultercompte")
    public String consulterCompte(Model model, String codeCompte)  {
        model.addAttribute("codeCompte", codeCompte);
        try {
            Compte compte = BanqueService.consulterCompte(codeCompte);
            Page<Operation> pageOperations = BanqueService.listeOperation(codeCompte, 0, 5);
            model.addAttribute("listeOperations", pageOperations.getContent());
            model.addAttribute("compte", compte);
        } catch (Exception e) {
           model.addAttribute("error", e);
        }
        
        return "compte";
    }


}