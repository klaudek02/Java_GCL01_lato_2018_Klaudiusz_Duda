package com.example.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.model.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {

	List<Manager> findByFirstName(String name);

}
