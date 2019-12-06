package com.banque.banque.services;

import com.banque.banque.entities.Compte;
import com.banque.banque.entities.Operation;

import org.springframework.data.domain.Page;

public interface IBanqueService {
    public Compte consulterCompte(String codeCompte);
    public void verser(String codeCompte, double montant);
    public void retirer(String codeCompte, double montant);
    public void virement(String codeCompteFrom,String codeCompteTo, double montant);
    public Page<Operation> listeOperation(String codeCompte, int page, int size);

}