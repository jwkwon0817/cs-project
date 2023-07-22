package me.jwkwon0817.cs.domain.repositories;

import me.jwkwon0817.cs.domain.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
	
	Patient findByOrderName(String orderName);
}
