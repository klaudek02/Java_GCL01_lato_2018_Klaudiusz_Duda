package com.example.project.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by rajeevkumarsingh on 21/11/17.
 */
@Entity
@Table(name = "manager")
public class Manager {
   
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
    @JsonIgnore
    private Country country;


    @NotNull
    private Long age;

    @OneToOne
    @JoinColumn(name = "club_id")
    private Club club_id;
    
    public Manager(Long id, @NotNull @Size(max = 100) String firstName, @NotNull @Size(max = 100) String lastName,
			Country country, Club club, @NotNull Long ag) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.age = age;
		this.club_id = club;
	}
	public Manager()
    {
    	
    }
	public Manager(@NotNull @Size(max = 100) String firstName, @NotNull @Size(max = 100) String lastName,
			Country country, Club club,@NotNull Long age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.age = age;
		this.club_id = club;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClub() {
		if(club_id != null)
		return club_id.getName();
		return "";
	}
	@JsonIgnore
	public Club getClub2()
	{
		return club_id;
	}
	public void setClub(Club club) {
		this.club_id = club;
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

	public Country getCountry() {
		return country;
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

	 @Override
		public String toString() {
			return "{id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", country=" + country.toString()
					+ ", age=" + age + "}";
		}


 
}
