package be.vdab.personeel.services;

import java.util.List;
import java.util.Optional;

import be.vdab.personeel.domain.Werknemer;

public interface WerknemerService {
	Optional<Werknemer> findById(long id);
	List<Werknemer> findOndergeschikten(long id);
	Optional<Werknemer> findByChefIsNull();

}
