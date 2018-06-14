package com.example.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.example.project.model.Club;
import com.example.project.model.Footballer;
import com.example.project.model.Manager;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {

	public static final String FIND_PROJECTS = "select b.country_id, a.name,c.first_name,c.last_name from club a inner join league b on a.league_id = b.id inner join footballer c on b.country_id = c.country_id";

	@Query(value = FIND_PROJECTS, nativeQuery = true)
	public List<Object[]> findProjects();
	

}