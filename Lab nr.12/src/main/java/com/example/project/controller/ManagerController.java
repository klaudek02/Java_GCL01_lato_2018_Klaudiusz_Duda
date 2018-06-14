package com.example.project.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
import com.example.project.model.Club;
import com.example.project.model.Country;
import com.example.project.model.League;
import com.example.project.model.Manager;
import com.example.project.model.Manager;
import com.example.project.repository.ClubRepository;
import com.example.project.repository.CountryRepository;
import com.example.project.repository.ManagerRepository;
import com.example.project.repository.ManagerRepository;

@Controller
public class ManagerController {
	@Autowired
	public ManagerRepository manager;
	@Autowired
	public CountryRepository country;
	@Autowired
	public ClubRepository club;

	@ResponseBody
	@PostMapping("/manager/all")
	public List<Manager> getAll() {
		return manager.findAll();
	}

	@PostMapping("/manager/add")
	public String addManager(Model model) {
		model.addAttribute("country",country.findAll());
		model.addAttribute("club",club.findAll());
		return "addManager";
	}

	@PostMapping("/manager/add2")
	@ResponseBody
	public String addManager2(String firstName, String lastName,
			@RequestParam String countryy, @RequestParam String clubb, Long age) {	
		Long country_id = Long.valueOf(countryy);
		Long club_id = Long.valueOf(clubb);
		Country countryA = country.findById(country_id).get();
		Club clubA = club.findById(club_id).get();
		if(clubA.getManager2() == null )
		{
			Manager managerA = new Manager(firstName,lastName,countryA,clubA,age);
			manager.save(managerA);
			return "Dodano poprawnie";
		}
		return "Ten klub ma juz menadzera";
	}

	/////////////////////////
	@PostMapping("/manager/update")
	public String updateManager(@RequestParam String value, Model model) {
		Long id = Long.valueOf(value);
		System.out.println(id);
		if (manager.existsById(id) != false) {
			model.addAttribute("id", id);
			model.addAttribute("country", country.findAll());
			model.addAttribute("club", club.findAll());
			return "updateManager";
		}
		return "redirect:/panel";
	}

	@PostMapping("/manager/update2")
	@ResponseBody
	public String updateManager2(String firstName, String lastName,
			@RequestParam String countryy,@RequestParam String clubb,  Long age, Long id) {
		Long country_id = Long.valueOf(countryy);
		Long club_id = Long.valueOf(clubb);
		Country countryA = country.findById(country_id).get();
		Club clubA = club.findById(club_id).get();
		Manager update = manager.findById(id).get();
		if(clubA.getManager2() == null || clubA.getManager2().getId() == id)
		{
			update.setFirstName(firstName);
			update.setLastName(lastName);
			update.setCountry(countryA);
			update.setAge(age);
			update.setClub(clubA);
			manager.save(update);
			return "Pomysle zaktualizowano!";
		}
		return "Ten klub ma juz menadzera";


	}

	@PostMapping("/manager/delete")
	@ResponseBody
	public String deleteManager(@RequestParam String value) {
		Long id = Long.valueOf(value);
		if (manager.existsById(id) != false) {
			Optional<Manager> ca = manager.findById(id);
			Manager remove = ca.get();
			
			try {
				manager.delete(remove);
				return "Usunieto poprawnie";
			} catch (Exception e) {
				return "Usun najpierw rekordy zalezne!";
			}

		}
		return "BLAD";
	}
	///////////////
	@GetMapping("manager/findbyname/{name}")
	@ResponseBody
	public List<Manager> desc(@PathVariable String name) {
		return manager.findByFirstName(name);
	}
	
  
}
