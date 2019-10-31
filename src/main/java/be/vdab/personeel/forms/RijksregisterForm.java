package be.vdab.personeel.forms;

import be.vdab.personeel.constraints.RijksregisterNr;

@RijksregisterNr
public class RijksregisterForm {

	private final long rijksregisternr;

	private final String geboortedatum;

	public RijksregisterForm(long rijksregisternr, String geboortedatum) {

		this.rijksregisternr = rijksregisternr;
		this.geboortedatum = geboortedatum;
	}

	public long getRijksregisternr() {
		return rijksregisternr;
	}

	public String getGeboortedatum() {
		return geboortedatum;
	}

}
