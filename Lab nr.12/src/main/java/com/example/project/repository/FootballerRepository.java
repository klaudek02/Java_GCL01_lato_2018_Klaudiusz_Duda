package com.example.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.model.Footballer;

@Repository
public interface FootballerRepository extends JpaRepository<Footballer, Long> {

	List<Footballer> findByCountryName(String name);

	List<Footballer> findByClubName(String name);

}