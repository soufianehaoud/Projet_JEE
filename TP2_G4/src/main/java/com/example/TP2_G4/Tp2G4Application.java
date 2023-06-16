package com.example.TP2_G4;

import ch.qos.logback.classic.pattern.DateConverter;
import com.example.TP2_G4.entities.Etudiant;
import com.example.TP2_G4.repos.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class Tp2G4Application implements CommandLineRunner {
	@Autowired //injection des dépendances , les implémentations
	EtudiantRepo repository;


	public static void main(String[] args) {
		SpringApplication.run(Tp2G4Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		repository.save(new Etudiant(null,"haoud","hay el baraka", new Date()));








	}
}
//maven : telechargement des dependances ;