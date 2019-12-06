package com.banque.banque.dao;

import com.banque.banque.entities.Operation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface OperationRepository extends JpaRepository<Operation, Long>{

        @Query("Select o from Operation o where o.compte.codeCompte = :x order by o.dateOperation DESC")
        public  Page<Operation> listeOperation(@Param("x") String codeCompte, Pageable pageable); 


        // Page<Operation> findAll(Operation predicate, Pageable page);
}