package com.banque.banque;


import java.util.Date;
import java.util.List;

import com.banque.banque.dao.ClientRepository;
import com.banque.banque.dao.CompteRepository;
import com.banque.banque.dao.OperationRepository;
import com.banque.banque.entities.Client;
import com.banque.banque.entities.Compte;
import com.banque.banque.entities.CompteCourant;
import com.banque.banque.entities.CompteEpargne;
import com.banque.banque.entities.Retrait;
import com.banque.banque.entities.Versement;
import com.banque.banque.services.IBanqueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;

	@Autowired
	private IBanqueService iBanqueService;
	public static void main( String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {


		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode("manon");
            System.out.println(hashedPassword+"*********");

		// Client client1 = clientRepository.save(new Client("Amar", "amaryassa@yahoo.fr"));
		// Client client2 = clientRepository.save(new Client("Manon", "manonRusque@gmail.com"));

		// Compte compte1 = compteRepository.save(new CompteCourant("c1", new Date(), 100000, client1, 5000));
		// Compte compte2 = compteRepository.save(new CompteEpargne("c2", new Date(), 50000, client2, 4.5));

		// operationRepository.save(new Versement(new Date(), 300, compte1));
		// operationRepository.save(new Versement(new Date(), 600, compte1));
		// operationRepository.save(new Versement(new Date(), 300, compte1));
		// operationRepository.save(new Retrait(new Date(), 200, compte1));

		// operationRepository.save(new Versement(new Date(), 200, compte2));
		// operationRepository.save(new Versement(new Date(), 300, compte2));
		// operationRepository.save(new Versement(new Date(), 400, compte2));
		// operationRepository.save(new Retrait(new Date(), 100, compte2));
		// List<Compte> comptes = compteRepository.findAll();
		// System.out.println("***********************************"+compteRepository.findAll()+"***********************************");


		// iBanqueService.verser("c1", 3);
		// iBanqueService.retirer("c1", 5000);
		// iBanqueService.retirer("c1", 1);
		// iBanqueService.virement("c1", "c2", 1000);

	}

}
