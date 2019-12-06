package com.banque.banque.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("cc") //la colummn type de compte = cc
public class CompteCourant extends Compte { 

    private double decouvert;

    public double getDecouvert() {
        return this.decouvert;
    }

    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }


    public CompteCourant() {
        super();
    }

    public CompteCourant(String codeCompte, Date dateCreation, double solde, Client client, double decouvert) {
        super(codeCompte, dateCreation, solde, client);
        this.decouvert = decouvert;
    }
    
    
}