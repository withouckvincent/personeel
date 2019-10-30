package be.vdab.personeel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.personeel.domain.Jobtitel;

public interface JobtitelRepository extends JpaRepository<Jobtitel, Long>{
	
}
