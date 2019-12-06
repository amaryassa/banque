package com.banque.banque.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client implements Serializable{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    private String nom;
    private String email;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Collection<Compte> comptes;

    public Client() {
        super();
    }

    public Client(String nom, String email) {
       
        this.nom = nom;
        this.email = email;
      
    }

    public Long getCode() {
        return this.code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Compte> getComptes() {
        return this.comptes;
    }

    public void setComptes(Collection<Compte> comptes) {
        this.comptes = comptes;
    }

    public Client code(Long code) {
        this.code = code;
        return this;
    }

    public Client nom(String nom) {
        this.nom = nom;
        return this;
    }

    public Client email(String email) {
        this.email = email;
        return this;
    }

    public Client comptes(Collection<Compte> comptes) {
        this.comptes = comptes;
        return this;
    }


 }