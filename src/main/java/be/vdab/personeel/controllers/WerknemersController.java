package be.vdab.personeel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
		werknemerService.findById().ifPresent(werknemer -> modelAndView.addObject(werknemer));
		modelAndView.addObject("ondergeschikten",werknemerService.findOndergeschikten(1));
		return modelAndView;
	}
}
