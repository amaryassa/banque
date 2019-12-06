package com.banque.banque.dao;

import com.banque.banque.entities.Compte;

import org.springframework.data.jpa.repository.JpaRepository;




public interface CompteRepository extends JpaRepository<Compte, String>{

}