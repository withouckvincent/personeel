package be.vdab.personeel.controllers;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.personeel.domain.Werknemer;
import be.vdab.personeel.forms.OpslagForm;
import be.vdab.personeel.forms.RijksregisterForm;
import be.vdab.personeel.services.WerknemerService;

@Controller
@RequestMapping("werknemersfiche")

class WerknemersController {

	private final WerknemerService werknemerService;
	

	WerknemersController(WerknemerService werknemerService) {
	this.werknemerService = werknemerService;
	
	}

	@GetMapping
	ModelAndView werknemersfiche() {
		ModelAndView modelAndView = new ModelAndView("werknemersfiche");
		werknemerService.findByChefIsNull().ifPresent(werknemer -> modelAndView.addObject(werknemer));
		modelAndView.addObject("ondergeschikten",werknemerService.findOndergeschikten(werknemerService.findByChefIsNull().get().getId()));
		return modelAndView;
	}
	
	@GetMapping("{optionalWerknemer}")
	ModelAndView read(@PathVariable Optional<Werknemer> optionalWerknemer) {
		ModelAndView modelAndView = new ModelAndView("werknemersfiche");
		optionalWerknemer.ifPresent(werknemer -> {
			modelAndView.addObject(werknemer);	
			modelAndView.addObject("ondergeschikten",werknemerService.findOndergeschikten(werknemer.getId()));
			if (werknemerService.findByChefIsNull().get().getId() != werknemer.getId()) {
				modelAndView.addObject("baas",werknemerService.findById(werknemer.getChef().getId()).get());	
			}
		});
		return modelAndView;
	}
	
	@GetMapping("/opslag/{optionalWerknemer}")
	public ModelAndView opslagBedrag(@PathVariable Optional<Werknemer> optionalWerknemer) {
		ModelAndView modelAndView = new ModelAndView("opslag");
		optionalWerknemer.ifPresent(werknemer -> {
			modelAndView.addObject(werknemer)
			.addObject(new OpslagForm(BigDecimal.ZERO));
		});
				
		return modelAndView;
	}

	@PostMapping("/opslag/{optionalWerknemer}")
	public ModelAndView ondernemingsNr(@PathVariable Optional<Werknemer> optionalWerknemer, @Valid OpslagForm form,
			Errors errors, RedirectAttributes redirect) {
		if (!optionalWerknemer.isPresent()) {
			return new ModelAndView("opslag");
		}
		Werknemer werknemer = optionalWerknemer.get();
		if (errors.hasErrors()) {
			return new ModelAndView("opslag").addObject(werknemer);
		}
		werknemer.opslag(form.getBedragOpslag());
		
		werknemerService.update(werknemer);
		
		redirect.addAttribute("id", werknemer.getId());
		return new ModelAndView("redirect:/werknemersfiche/{id}");
	}

	@GetMapping("/rijksregisternummer/{optionalWerknemer}")
	public ModelAndView rijksregisternr(@PathVariable Optional<Werknemer> optionalWerknemer) {
		ModelAndView modelAndView = new ModelAndView("rijksregisternummer");
		optionalWerknemer.ifPresent(werknemer -> {
			modelAndView.addObject(werknemer);		
			System.out.println(werknemer.getGeboorte().format(DateTimeFormatter.ISO_DATE));
			String datum = werknemer.getGeboorte().format(DateTimeFormatter.ISO_DATE);
			modelAndView.addObject(new RijksregisterForm(werknemer.getRijksregisternr(),datum));
		});
				
		return modelAndView;
	}
	
	@PostMapping("/rijksregisternummer/{optionalWerknemer}")
	public ModelAndView rijksregisternr(@PathVariable Optional<Werknemer> optionalWerknemer, @Valid RijksregisterForm form,
			Errors errors, RedirectAttributes redirect) {
		if (!optionalWerknemer.isPresent()) {
			return new ModelAndView("rijksregisternummer");
		}
		Werknemer werknemer = optionalWerknemer.get();
		if (errors.hasErrors()) {
			return new ModelAndView("rijksregisternummer").addObject(werknemer);
		}
		werknemer.setRijksregisternr(form.getRijksregisternr());

		werknemerService.update(werknemer);
		
		redirect.addAttribute("id", werknemer.getId());
		return new ModelAndView("redirect:/werknemersfiche/{id}");
	}
	
	
}
