package be.vdab.personeel.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.personeel.domain.Werknemer;
import be.vdab.personeel.services.WerknemerService;

@Controller
@RequestMapping("werknemersfiche")

class WerknemersController {

	private final WerknemerService werknemerService;
	//private Werknemer tijdelijkeWerknemer;

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
}
