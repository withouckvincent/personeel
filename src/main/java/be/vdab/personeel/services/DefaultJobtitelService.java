package be.vdab.personeel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.personeel.domain.Jobtitel;
import be.vdab.personeel.repositories.JobtitelRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultJobtitelService implements JobtitelService {

	private final JobtitelRepository jobtitelRepository;

	DefaultJobtitelService(JobtitelRepository jobtitelRepository) {
		this.jobtitelRepository = jobtitelRepository;

	}

	@Override
	public List<Jobtitel> findAll() {

		return jobtitelRepository.findAll();

	}

	@Override
	public Optional<Jobtitel> findById(long id) {

		return jobtitelRepository.findById(id);

	}

}
