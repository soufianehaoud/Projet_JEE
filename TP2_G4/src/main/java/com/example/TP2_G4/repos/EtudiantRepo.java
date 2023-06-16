package com.example.TP2_G4.repos;

import com.example.TP2_G4.entities.Etudiant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EtudiantRepo extends JpaRepository<Etudiant,Integer> {
    Page<Etudiant> findByFullnameContains(String fullname, Pageable p);
}
