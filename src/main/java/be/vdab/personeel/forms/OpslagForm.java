package be.vdab.personeel.forms;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class OpslagForm {
	@DecimalMin("1.00")
	@NumberFormat(style = Style.NUMBER, pattern =  "#,##0.00")
	@Digits(integer = 10, fraction = 2)	
	private final BigDecimal bedragOpslag;


	public OpslagForm(BigDecimal bedragOpslag) {
		
		this.bedragOpslag = bedragOpslag;
	}


	public BigDecimal getBedragOpslag() {
		return bedragOpslag;
	}


	
	
}
