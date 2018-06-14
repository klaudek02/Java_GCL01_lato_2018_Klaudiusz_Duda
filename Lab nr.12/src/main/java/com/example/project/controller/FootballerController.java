package com.example.project.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.exception.ResourceNotFoundException;
import com.example.project.model.Footballer;
import com.example.project.model.League;
import com.example.project.model.Footballer;
import com.example.project.model.Country;
import com.example.project.model.Club;
import com.example.project.repository.FootballerRepository;
import com.example.project.repository.FootballerRepository;
import com.example.project.repository.CountryRepository;
import com.example.project.repository.ClubRepository;

@Controller
public class FootballerController {
	
	@Autowired
	public FootballerRepository footballer;
	@Autowired
	public CountryRepository country;
	@Autowired
	public ClubRepository club;

	@ResponseBody
	@PostMapping("/footballer/all")
	public List<Footballer> getAll() {
		return footballer.findAll();
	}

	@PostMapping("/footballer/add")
	public String addFootballer(Model model) {
		model.addAttribute("country", country.findAll());
		model.addAttribute("club",club.findAll());
		return "addFootballer";
	}

	@PostMapping("/footballer/add2")
	public String addFootballer2(String firstName, String lastName, Long age, String position,@RequestParam String countryy,
			 @RequestParam String clubb) {	
		Long country_id = Long.valueOf(countryy);
		Long club_id = Long.valueOf(clubb);
		Country countryA = country.findById(country_id).get();
		Club clubA = club.findById(club_id).get();
		Footballer footballerA = new Footballer(firstName,lastName,countryA,age,position,clubA);
		footballer.save(footballerA);
		return "redirect:/panel";
	}

	/////////////////////////
	@PostMapping("/footballer/update")
	public String updateFootballer(@RequestParam String value, Model model) {
		Long id = Long.valueOf(value);
		System.out.println(id);
		if (footballer.existsById(id) != false) {
			model.addAttribute("id", id);
			model.addAttribute("country", country.findAll());
			model.addAttribute("club",club.findAll());
			return "updateFootballer";
		}
		return "redirect:/panel";
	}

	@PostMapping("/footballer/update2")
	@ResponseBody
	public String updateFootballer2(String firstName, String lastName,Long age, String position,
			@RequestParam String countryy, @RequestParam String clubb, Long id) {
		Long country_id = Long.valueOf(countryy);
		Long club_id = Long.valueOf(clubb);
		Country countryA = country.findById(country_id).get();
		Club clubA = club.findById(club_id).get();
		Footballer update = footballer.findById(id).get();
		update.setFirstName(firstName);
		update.setLastName(lastName);
		update.setCountry(countryA);
		update.setAge(age);
		update.setPosition(position);
		update.setClub(clubA);
		
		footballer.save(update);
		return "Pomysle zaktualizowano!";

	}

	@PostMapping("/footballer/delete")
	@ResponseBody
	public String deleteFootballer(@RequestParam String value) {
		Long id = Long.valueOf(value);
		if (footballer.existsById(id) != false) {
			Optional<Footballer> ca = footballer.findById(id);
			Footballer remove = ca.get();
			try {
				footballer.delete(remove);
				return "Usunieto poprawnie";
			} catch (Exception e) {
				return "Usun najpierw rekordy zalezne!";
			}

		}
		return "BLAD";
	}

	/////////////////////////
	@GetMapping("footballer/findbycountry/{name}")
	@ResponseBody
	public List<Footballer> desc(@PathVariable String name) {
		return footballer.findByCountryName(name);
	}
	@GetMapping("footballer/findbyclub/{name}")
	@ResponseBody
	public List<Footballer> findbyclub(@PathVariable String name)
	{
		return footballer.findByClubName(name);
	}
	/*@GetMapping("footballer/findhomegrown")
	@ResponseBody
	public Map<Country, List<Footballer>> findHomegrown(@PathVariable String name)
	{
		List<Country> 
	}*/
}
