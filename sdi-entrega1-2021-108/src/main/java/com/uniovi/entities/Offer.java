package com.uniovi.entities;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "offer")
public class Offer {

	@Id
	@GeneratedValue
	private Long Id;
	
	private String title;
	private String description;
	private double price;
	private Date date;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "user_buyer")
	private User buyer;
	
	public Offer() {}
	
	public Offer(String title, String description, double price, User user) {
		super();
		this.title = title;
		this.description = description;
		this.date = Date.valueOf(LocalDate.now());
		this.price = price;
		this.user = user;
	}
	
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	@Override
	public String toString() {
		return "Offer [Id=" + Id + ", title=" + title + ", description=" + description + ", price=" + price + ", date="
				+ date + ", user=" + user + "]";
	}
	
}
