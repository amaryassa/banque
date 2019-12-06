package com.banque.banque.dao;

import com.banque.banque.entities.Client;

import org.springframework.data.jpa.repository.JpaRepository;



public interface ClientRepository extends JpaRepository<Client, Long>{
	

}