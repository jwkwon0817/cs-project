package me.jwkwon0817.cs.domain.repositories;

import me.jwkwon0817.cs.domain.entities.PatientFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientFileRepository extends JpaRepository<PatientFile, Long> {
	
	PatientFile findByPatientOrderName(String orderName);
}
