package com.example.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.model.Country;
import com.example.project.model.League;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

	  public List<Country> findByName(String name);
	  public List<Country> findByDescription(String description);
}
