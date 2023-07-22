package me.jwkwon0817.cs.domain.repositories;

import me.jwkwon0817.cs.domain.entities.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {
	
	List<Variant> findAllByPatientOrderName(String orderName);
}
