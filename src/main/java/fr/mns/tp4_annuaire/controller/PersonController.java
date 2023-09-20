package fr.mns.tp4_annuaire.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.mns.tp4_annuaire.model.Person;
import fr.mns.tp4_annuaire.repository.PersonRepository;
import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/persons")
public class PersonController {
	
	private final PersonRepository repository;

	public PersonController( PersonRepository repository ) {
		this.repository = repository;
	}

	@GetMapping
	public ModelAndView getPersons() {
		ModelAndView mv = new ModelAndView("persons");
		mv.addObject("persons", repository.findAll());
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView getPerson(@PathVariable("id") Long id) {
		if (id != null) {
			Optional<Person> person = repository.findById(id);
			if (person.isPresent()) {
				ModelAndView mv = new ModelAndView("person");
				mv.addObject("person", person.get());
				return mv;
			}
		}
		return getPersons();

	}
	@GetMapping("/addPerson")
	public ModelAndView createPersonForm() {
		ModelAndView mv=new ModelAndView("personForm");
		mv.addObject("person", new Person());
		return mv;
		
	}
	
	@PostMapping("/addPerson")
	public String savePerson(@ModelAttribute("personForm") Person person) {
		person.setId(null);
		person.setBirthDate(LocalDate.now());
		repository.save(person);
		
		return "redirect:/persons";
		
	}
	
	@GetMapping("/delete/{id}")
	public String deletePerson(@PathVariable("id") Long id) {
		repository.deleteById(id);
		
		return "redirect:/persons";
		
	}

}
