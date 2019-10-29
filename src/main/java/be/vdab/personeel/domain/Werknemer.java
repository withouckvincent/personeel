package be.vdab.personeel.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;



	@Entity
	@Table(name = "werknemers")
	public class Werknemer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	@NotBlank
	private String familienaam;

	@NotBlank
	private String voornaam;

	@Column(unique = true)
	@NotBlank
	private String email;


	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "chefid")

	
	private Werknemer chef;
	
	
	
	@NotNull
	@PositiveOrZero
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "jobtitelId")
	@NotNull
	private Jobtitel jobtitel;

	
	@NotNull
	@PositiveOrZero
	@NumberFormat(style = Style.NUMBER)
	@Digits(integer = 10, fraction = 2)
	private BigDecimal salaris;
	
	@NotNull
	private String paswoord;
	
	@DateTimeFormat(style = "S-")
	@NotNull
	private LocalDate geboorte;

	
	@Column(unique = true)
	@NotNull
	private long rijksregisternr;
	

	@Version
	@NotNull
	private long versie;


	public long getId() {
		return id;
	}


	public String getFamilienaam() {
		return familienaam;
	}


	public String getVoornaam() {
		return voornaam;
	}


	public String getEmail() {
		return email;
	}


	public Werknemer getChef() {
		return chef;
	}


	public Jobtitel getJobtitel() {
		return jobtitel;
	}


	public BigDecimal getSalaris() {
		return salaris;
	}


	public String getPaswoord() {
		return paswoord;
	}


	public LocalDate getGeboorte() {
		return geboorte;
	}


	public long getRijksregisternr() {
		return rijksregisternr;
	}


	public long getVersie() {
		return versie;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (rijksregisternr ^ (rijksregisternr >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Werknemer other = (Werknemer) obj;
		if (rijksregisternr != other.rijksregisternr)
			return false;
		return true;
	}

	
	
	
	
	}
	
	

