package com.example.project.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.xml.ws.Service.Mode;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.exception.ResourceNotFoundException;
import com.example.project.model.Country;
import com.example.project.model.League;
import com.example.project.repository.CountryRepository;
import com.example.project.repository.LeagueRepository;

@Controller
public class CountryController {

	@Autowired
	public CountryRepository country;
	
	@ResponseBody
	@PostMapping("/country/all")
	public List<Country> getAll()
	{
		return country.findAll();
	}
	
	@PostMapping("/country/add")
	public String addClub(Model model)
	{
		model.addAttribute("clubs", country.findAll());
		return "addCountry";
	}
	
	@PostMapping("/country/add2")
	public String addClub2(String name,String description, Long population)
	{
		Country countryA = new Country(name, description, population);
        country.save(countryA);
        return "redirect:/panel";
	}
	
/////////////////////////
	@PostMapping("/country/update")
	public String updateCountry(@RequestParam String value , Model model)
	{
		Long id = Long.valueOf(value);
		System.out.println(id);
		if(country.existsById(id) != false)
		{
			model.addAttribute("id", id);
			return "updateCountry";
		}
		return "redirect:/panel";
	}
	@PostMapping("/country/update2")
	public String updateCountry2(String name,String description, Long population, Long id)
	{
		Country update = country.findById(id).get();
		update.setName(name);
		update.setDescription(description);
		update.setPopulation(population);
        country.save(update);
        return "redirect:/panel";
	}
	@PostMapping("/country/delete")
	@ResponseBody
	public String deleteCountry(@RequestParam String value)
	{
		Long id = Long.valueOf(value);
		if(country.existsById(id) != false){
			Optional<Country> ca = country.findById(id);
			Country remove = ca.get();
			try {
				country.delete(remove);
				return "Usunieto poprawnie";
			}catch(Exception e)
			{
				return "Usun najpierw rekordy zalezne!";
			}
				
		}
		return "BLAD";
	}
/////////////////////////
	
	@GetMapping("country/findbydescription/{desc}")
	@ResponseBody
	public List<Country> desc(@PathVariable String desc)
	{
		return country.findByDescription(desc);
	}
	@GetMapping("country/findbyname/{desc}")
	@ResponseBody
	public List<Country> name(@PathVariable String name)
	{
		return country.findByName(name);
	}
//	@GetMapping("country/findleagues/")

	
}
