package com.example.project.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.model.Country;
import com.example.project.model.League;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {

	List<League> findByName(String name);

	List<League> findByCountry(Country countryA);
}