package com.uniovi.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue
	private Long Id;
	@Column(unique = true)
	private String name;
	private String surname;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	@Transient
	private String confirmPassword;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Offer> offers;
	
	private String role;
	
	private double money = 100;
	
	public User(String email, String name, String surname) {
		this.name = name;
		this.surname = surname;
		this.email = email;
	}
	
	public User() {}
	
	public Long getId() {
		return Id;
	}
	
	public void setId(Long id) {
		Id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public double getMoney() {
		return money;
	}
	
	public void setMoney(double money) {
		this.money = money;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

	public Set<Offer> getOffers() {
		return offers;
	}

	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}

	@Override
	public String toString() {
		return "User [Id=" + Id + ", name=" + name + ", surname=" + surname + ", email=" + email
				+ ", password=" + password + ", confirmPassword=" + confirmPassword + ", role=" + role + ", money="
				+ money + "]";
	}
	
	
	
	
	
}

