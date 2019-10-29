package be.vdab.personeel.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.personeel.domain.Werknemer;
import be.vdab.personeel.repositories.WerknemerRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultWerknemerService implements WerknemerService {

	private final WerknemerRepository werknemerRepository;

	DefaultWerknemerService(WerknemerRepository werknemerRepository) {
		this.werknemerRepository = werknemerRepository;
	}

	@Override
	public Optional<Werknemer> findById(long id) {

		return werknemerRepository.findById(id);
	}

	@Override
	public Optional<Werknemer> findByChefIsNull() {

		return werknemerRepository.findByChefIsNull();
	}

	@Override
	public List<Werknemer> findOndergeschikten(long id) {
		List<Werknemer> ondergeschiktenList = new ArrayList<Werknemer>();
		werknemerRepository.findAll().forEach(werknemer -> {
			if (werknemer.getChef() != null) {
				if (id != werknemer.getId()) {
					if (werknemer.getChef().getId() == id) {
						ondergeschiktenList.add(werknemer);
					}
				}
			}
		});
		return ondergeschiktenList;
	}
}
