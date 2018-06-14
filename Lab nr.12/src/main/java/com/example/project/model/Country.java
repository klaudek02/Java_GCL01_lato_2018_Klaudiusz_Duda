package com.example.project.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by rajeevkumarsingh on 21/11/17.
 */
@Entity
@Table(name = "country")
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(max = 100)
	@Column(unique = true)
	private String name;

	@NotNull
	@Size(max = 250)
	private String description;

	@NotNull
	private Long population;

	@OneToMany(mappedBy = "country")
	@JsonIgnore
	private List<Footballer> footballer;

	@OneToMany(mappedBy = "country")
	@JsonIgnore
	private List<Manager> manager;

	@OneToMany(mappedBy = "country")
	@JsonIgnore
	private List<League> league;
	{

	}

	public Country() {

	}

	public Country(@NotNull @Size(max = 100) String name, @NotNull @Size(max = 250) String description,
			@NotNull Long population) {
		super();
		this.name = name;
		this.description = description;
		this.population = population;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPopulation() {
		return population;
	}

	public void setPopulation(Long population) {
		this.population = population;
	}

	public List<Footballer> getFootballer() {
		return footballer;
	}

	public void setFootballer(List<Footballer> footballer) {
		this.footballer = footballer;
	}

	public List<Manager> getManager() {
		return manager;
	}

	public void setManager(List<Manager> manager) {
		this.manager = manager;
	}

	public List<League> getLeague() {
		return league;
	}

	public void setLeague(List<League> league) {
		this.league = league;
	}

	@Override
	public String toString() {
		return "{id=" + id + ", name=" + name + ", description=" + description + ", population=" + population + "}";

	}

}
