
package com.banque.banque.services;

import java.util.Date;

import com.banque.banque.dao.CompteRepository;
import com.banque.banque.dao.OperationRepository;
import com.banque.banque.entities.Compte;
import com.banque.banque.entities.CompteCourant;
import com.banque.banque.entities.Operation;
import com.banque.banque.entities.Retrait;
import com.banque.banque.entities.Versement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BanqueServiceImpl implements IBanqueService {
    
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private OperationRepository operationRepository;
    
    @Override
    public Compte consulterCompte(String codeCompte) {
        Compte compte= compteRepository.findById(codeCompte).orElse(null);;
        if(compte == null) throw new  RuntimeException("Compte introuvable");
        return compte;
    }

    @Override
    public void verser(String codeCompte, double montant) {
        Compte compte = consulterCompte(codeCompte);
        Versement v= new Versement(new Date(), montant, compte);
        operationRepository.save(v);
        compte.setSolde(compte.getSolde() + montant);
        compteRepository.save(compte);

        
    }

    @Override
    public void retirer(String codeCompte, double montant) {
        Compte compte = consulterCompte(codeCompte);
        double decouvert = 0;
        if(compte instanceof CompteCourant)
            decouvert = ((CompteCourant) compte).getDecouvert();
        
        if((compte.getSolde() + decouvert) < montant)
            throw new RuntimeException("Vous n'avez pas assez d'argent dans votre compte pour effectuer un retrait");
        
        Retrait r= new Retrait (new Date(), montant, compte);
        operationRepository.save(r);
        compte.setSolde(compte.getSolde() - montant);
        compteRepository.save(compte);

        

    }

    @Override
    public void virement(String codeCompteFrom, String codeCompteTo, double montant) {
        retirer(codeCompteFrom,montant);
        verser(codeCompteTo,montant);
    }

    @Override
    public Page<Operation> listeOperation(String codeCompte, int page, int size) {
        
        return operationRepository.listeOperation(codeCompte, new PageRequest(page, size));
    }



}