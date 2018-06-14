package com.example.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project.model.Club;
import com.example.project.model.Country;
import com.example.project.model.Footballer;
import com.example.project.model.League;
import com.example.project.model.Manager;
import com.example.project.repository.ClubRepository;
import com.example.project.repository.CountryRepository;
import com.example.project.repository.FootballerRepository;
import com.example.project.repository.LeagueRepository;
import com.example.project.repository.ManagerRepository;

@Controller
public class MainController {

	@Autowired
	public CountryRepository country;
	@Autowired
	public LeagueRepository league;
	@Autowired
	public ClubRepository club;
	@Autowired 
	public FootballerRepository footballer;
	@Autowired
	public ManagerRepository manager;
	
	@GetMapping("/panel")
	public String getPanel(Model model)
	{
		model.addAttribute("country", country.findAll());
		model.addAttribute("league", league.findAll());
		model.addAttribute("club", club.findAll());
		model.addAttribute("footballer", footballer.findAll());
		model.addAttribute("manager", manager.findAll());
		return "panel";
    }	
			
}
