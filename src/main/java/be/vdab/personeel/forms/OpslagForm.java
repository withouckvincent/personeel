package be.vdab.personeel.forms;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;

public class OpslagForm {
	@DecimalMin("1.00")
	
	private final BigDecimal bedragOpslag;


	public OpslagForm(BigDecimal bedragOpslag) {
		
		this.bedragOpslag = bedragOpslag;
	}


	public BigDecimal getBedragOpslag() {
		return bedragOpslag;
	}


	
	
}
