package be.vdab.personeel.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import be.vdab.personeel.forms.RijksregisterForm;

public class RijksregisterNrValidator implements ConstraintValidator<RijksregisterNr, RijksregisterForm> {
	@Override
	public void initialize(RijksregisterNr rijksregistenr) {
	}

	@Override
	public boolean isValid(RijksregisterForm rijksregisterForm, ConstraintValidatorContext context) {
		if (rijksregisterForm.getRijksregisternr() == 0) {
			return true;
		}
		long laatste2Cijfers = rijksregisterForm.getRijksregisternr() % 100L;
		long overigeCijfers = rijksregisterForm.getRijksregisternr() / 100;

		int jaar = Integer.parseInt(rijksregisterForm.getGeboortedatum().substring(0, 4));
		int jaarklein = Integer.parseInt(rijksregisterForm.getGeboortedatum().substring(2, 4));
		int maand = Integer.parseInt(rijksregisterForm.getGeboortedatum().substring(6, 7));
		int dag = Integer.parseInt(rijksregisterForm.getGeboortedatum().substring(9, 10));

		String s2 = Long.toString(rijksregisterForm.getRijksregisternr());
		int jaar2 = Integer.parseInt(s2.substring(0, 2));
		int maand2 = Integer.parseInt(s2.substring(3, 4));
		int dag2 = Integer.parseInt(s2.substring(5, 6));

		if ((dag != dag2) || (maand != maand2) || (jaarklein != jaar2)) {
			return false;
		}

		if (jaar > 2000) {
			overigeCijfers = overigeCijfers + 2000000000;
		}

		return laatste2Cijfers == 97 - overigeCijfers % 97;
	}
}