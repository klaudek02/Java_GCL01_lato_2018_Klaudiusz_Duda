package com.example.project.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by rajeevkumarsingh on 21/11/17.
 */
@Entity
@Table(name = "club")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "league_id", nullable = false)
    private League league;

    private Long finance;
    private String sponsor;

    @OneToOne(mappedBy = "club_id")
    private Manager manager;

    @OneToMany(mappedBy="club")
    @JsonIgnore
    private List<Footballer> footballer;
    

	public Club()
    {
    	
    }
	public Club(@NotNull String name, League league, Long finance, String sponsor) {
		super();
		this.name = name;
		this.league = league;
		this.finance = finance;
		this.sponsor = sponsor;
	}
	public String getManager() {
		if(manager !=null)
		return manager.toString();
		return "";
	}
	@JsonIgnore
	public Manager getManager2()
	{
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
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

	public String getLeague() {
		return league.toString();
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public Long getFinance() {
		return finance;
	}

	public void setFinance(Long finance) {
		this.finance = finance;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
    public List<Footballer> getFootballer() {
		return footballer;
	}
	public void setFootballer(List<Footballer> footballer) {
		this.footballer = footballer;
	}
	@Override
	public String toString() {
		return "{id=" + id + ", name=" + name + ", league=" + league.toString() + ", finance=" + finance + ", sponsor="
				+ sponsor + ", manager=" + manager.toString() + "]";
	}
	



}
