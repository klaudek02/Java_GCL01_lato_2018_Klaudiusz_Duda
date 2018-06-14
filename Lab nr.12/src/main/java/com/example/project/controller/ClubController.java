package com.example.project.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
import com.example.project.model.Footballer;
import com.example.project.model.League;
import com.example.project.model.Manager;
import com.example.project.model.Club;
import com.example.project.model.Club;
import com.example.project.repository.ClubRepository;
import com.example.project.repository.LeagueRepository;
import com.example.project.repository.ManagerRepository;


import com.example.project.repository.ClubRepository;
import com.example.project.repository.ClubRepository;

@Controller
public class ClubController {

	@Autowired
	public ClubRepository club;
	@Autowired
	public LeagueRepository league;
	@Autowired
	public ManagerRepository manager;

	@ResponseBody
	@PostMapping("/club/all")
	public List<Club> getAll() {
		return club.findAll();
	}

	@PostMapping("/club/add")
	public String addClub(Model model) {
		model.addAttribute("league", league.findAll());
		return "addClub";
	}

	@PostMapping("/club/add2")
	public String addClub2(String name, Long finance, String sponsor, @RequestParam String leaguee) {	
		Long league_id = Long.valueOf(leaguee);
		League leagueA = league.findById(league_id).get();
		Club clubA = new Club(name, leagueA,finance, sponsor);
		club.save(clubA);
		return "redirect:/panel";
	}

	/////////////////////////
	@PostMapping("/club/update")
	public String updateClub(@RequestParam String value, Model model) {
		Long id = Long.valueOf(value);
		System.out.println(id);
		if (club.existsById(id) != false) {
			model.addAttribute("id", id);
			model.addAttribute("league", league.findAll());
			return "updateClub";
		}
		return "redirect:/panel";
	}

	@PostMapping("/club/update2")
	@ResponseBody
	public String updateClub2(String name, Long finance, String sponsor, @RequestParam String leaguee, Long id) {
		Long league_id = Long.valueOf(leaguee);
		League leagueA = league.findById(league_id).get();
		Club update = club.findById(id).get();
			update.setName(name);
			update.setFinance(finance);
			update.setSponsor(sponsor);
			update.setLeague(leagueA);
			club.save(update);
			return "Pomysle zaktualizowano!";
	}

	@PostMapping("/club/delete")
	@ResponseBody
	public String deleteClub(@RequestParam String value) {
		Long id = Long.valueOf(value);
		if (club.existsById(id) != false) {
			Optional<Club> ca = club.findById(id);
			Club remove = ca.get();
			try {
				club.delete(remove);
				return "Usunieto poprawnie";
			} catch (Exception e) {
				return "Usun najpierw rekordy zalezne!";
			}

		}
		return "BLAD";
	}

	/////////////////////////
	@GetMapping("club/showfootballers/{id}")
	@ResponseBody
	public List<Footballer> desc(@PathVariable Long id)
	{
		Club cc =  club.findById(id).get();
		return cc.getFootballer();
	}
	@GetMapping("club/showhomegrown")
	@ResponseBody
	public String dec() throws JSONException 
	{
		List<Object[]> list = club.findProjects();
		JSONArray array = new JSONArray();
		for(int i = 0 ; i< list.size() ; i++) {
			Object []p = list.get(i);
			JSONObject json = new JSONObject();
			json.put("country_id", p[0]);
			json.put("last_name", p[3]);
			json.put("first_name", p[2]);
			json.put("club_name", p[1]);
			array.put(json);
		};
		return array.toString();
	};
}



