package be.vdab.personeel.services;

import java.util.List;
import java.util.Optional;

import be.vdab.personeel.domain.Werknemer;

public interface WerknemerService {
	Optional<Werknemer> findById();
	List<Werknemer> findOndergeschikten(long id);
}
