package be.vdab.personeel.services;

import java.util.List;
import java.util.Optional;

import be.vdab.personeel.domain.Jobtitel;

public interface JobtitelService {
	List<Jobtitel> findAll();
	Optional<Jobtitel> findById(long id);
}
