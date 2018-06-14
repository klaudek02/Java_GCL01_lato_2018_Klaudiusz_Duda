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
import com.example.project.model.Country;
import com.example.project.model.Footballer;
import com.example.project.model.League;
import com.example.project.model.League;
import com.example.project.repository.CountryRepository;
import com.example.project.repository.LeagueRepository;
import com.example.project.repository.LeagueRepository;

@Controller
public class LeagueController {
	@Autowired
	public LeagueRepository league;
	@Autowired
	public CountryRepository country;

	@ResponseBody
	@PostMapping("/league/all")
	public List<League> getAll() {
		return league.findAll();
	}

	@PostMapping("/league/add")
	public String addClub(Model model) {
		model.addAttribute("clubs", league.findAll());
		model.addAttribute("country", country.findAll());
		return "addLeague";
	}

	@PostMapping("/league/add2")
	public String addClub2(String name, @RequestParam String value) {
		Long id = Long.valueOf(value);
		Country countryA = country.findById(id).get();
		League leagueA = new League(name, countryA);
		league.save(leagueA);
		return "redirect:/panel";
	}

	/////////////////////////
	@PostMapping("/league/update")
	public String updateLeague(@RequestParam String value, Model model) {
		Long id = Long.valueOf(value);
		if (league.existsById(id) != false) {
			model.addAttribute("id", id);
			model.addAttribute("country", country.findAll());
			return "updateLeague";
		}
		return "redirect:/panel";
	}

	@PostMapping("/league/update2")
	public String updateLeague2(String name, @RequestParam String value, Long id) {
		Long iid = Long.valueOf(value);
		Country countryA = country.findById(iid).get();
		League update = league.findById(id).get();
		update.setName(name);
		update.setCountry(countryA);
		league.save(update);
		return "redirect:/panel";
	}

	@PostMapping("/league/delete")
	@ResponseBody
	public String deleteLeague(@RequestParam String value) {
		Long id = Long.valueOf(value);
		if (league.existsById(id) != false) {
			Optional<League> ca = league.findById(id);
			League remove = ca.get();
			try {
				league.delete(remove);
				return "Usunieto poprawnie";
			} catch (Exception e) {
				return "Usun najpierw rekordy zalezne!";
			}

		}
		return "BLAD";
	}

	/////////////////////////
	@GetMapping("league/findbyname/{name}")
	@ResponseBody
	public List<League> desc(@PathVariable String name) {
		return league.findByName(name);
	}

	@GetMapping("league/findbyid/{id}")
	@ResponseBody
	public Optional<League> idname(@PathVariable Long id) {
		return league.findById(id);
	}
	@GetMapping("league/findbycountry/{name}")
	@ResponseBody
	public List<League> bycountry(@PathVariable String name)
	{
		Country countryA = country.findByName(name).get(0);
		return league.findByCountry(countryA);
	}
	
}
