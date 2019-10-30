package be.vdab.personeel.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "jobtitels")
public class Jobtitel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank
	private String naam;

	@Version
	@NotNull
	private long versie;

	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}

	public long getVersie() {
		return versie;
	}
	
	@OneToMany(mappedBy = "jobtitel") 
	private Set<Werknemer> werknemers; 
	
	public Set<Werknemer> getWerknemers() {
	return Collections.unmodifiableSet(werknemers);
	}
	

}