package be.vdab.personeel.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.personeel.domain.Jobtitel;
import be.vdab.personeel.services.JobtitelService;

@Controller
@RequestMapping("jobtitels")

class JobtitelsController {
	
	private final JobtitelService jobtitelService;


	JobtitelsController(JobtitelService jobtitelService) {
	this.jobtitelService = jobtitelService;
	
	}
	

	@GetMapping
	ModelAndView jobtitels() {
		ModelAndView modelAndView = new ModelAndView("jobtitels");
		
		modelAndView.addObject("jobtitels",jobtitelService.findAll());
		
		return modelAndView;
	}
	
	@GetMapping("{optionalJobtitel}")
	ModelAndView read(@PathVariable Optional<Jobtitel> optionalJobtitel) {
		ModelAndView modelAndView = new ModelAndView("jobtitels");
		modelAndView.addObject("jobtitels",jobtitelService.findAll());
		optionalJobtitel.ifPresent(jobtitel -> {
			modelAndView.addObject("jobtitelHoofding",jobtitel.getNaam());
			modelAndView.addObject("jobtitelWerknemers",jobtitel.getWerknemers());
		});
		
		
		
		return modelAndView;
	}	
	
}
