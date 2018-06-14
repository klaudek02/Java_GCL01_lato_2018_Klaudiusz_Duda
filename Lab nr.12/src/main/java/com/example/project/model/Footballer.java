package com.example.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by rajeevkumarsingh on 21/11/17.
 */
@Entity
@Table(name = "footballer")
public class Footballer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(max = 100)
    @Column(name="first_name")
    private String firstName;

    @NotNull
    @Size(max = 100)
    @Column(name="last_name")
    private String lastName;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;


    @NotNull
    private Long age;
    
    private String position;

    @ManyToOne
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    public Footballer()
    {
    	
    }
	public Footballer(@NotNull @Size(max = 100) String firstName, @NotNull @Size(max = 100) String lastName,
			Country country, @NotNull Long age, String position, Club club) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.age = age;
		this.position = position;
		this.club = club;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getCountry() {
		return country.toString();
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getClub() {
		return club.getName();
	}

	public void setClub(Club club) {
		this.club = club;
	}

   


}
