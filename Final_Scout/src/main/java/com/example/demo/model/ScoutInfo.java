package com.example.demo.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class ScoutInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length = 40)
	private String player;
	
	@Column(nullable = false)
	private int age;
	
	@Column(nullable = false, length = 30)
	private String region;
	
	@Column(nullable = false, length = 15 )
	private String position;
	
	@Column(nullable = false, length = 30)
	private String playstyle;
	
	@Column(nullable = false )
	private double price;
	
	@Column(nullable = false )
	private boolean injured;
	
	@Column(nullable = false, length = 250 )
	private String details;
	
	
	public ScoutInfo() {
		super();
	}

	//Constructor with ID
	public ScoutInfo(long id, String player, int age, String region, String position, String playstyle, double price,
			boolean injured, String details) {
		super();
		this.id = id;
		this.player = player;
		this.age = age;
		this.region = region;
		this.position = position;
		this.playstyle = playstyle;
		this.price = price;
		this.injured = injured;
		this.details = details;
	}

	//Without ID
	public ScoutInfo(String player, int age, String region, String position, String playstyle, double price,
			boolean injured, String details) {
		super();
		this.player = player;
		this.age = age;
		this.region = region;
		this.position = position;
		this.playstyle = playstyle;
		this.price = price;
		this.injured = injured;
		this.details = details;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPlaystyle() {
		return playstyle;
	}

	public void setPlaystyle(String playstyle) {
		this.playstyle = playstyle;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isInjured() {
		return injured;
	}

	public void setInjured(boolean injured) {
		this.injured = injured;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, details, id, injured, player, playstyle, position, price, region);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScoutInfo other = (ScoutInfo) obj;
		return age == other.age && Objects.equals(details, other.details) && id == other.id && injured == other.injured
				&& Objects.equals(player, other.player) && Objects.equals(playstyle, other.playstyle)
				&& Objects.equals(position, other.position) && price == other.price
				&& Objects.equals(region, other.region);
	}

	@Override
	public String toString() {
		return "ScoutInfo [id=" + id + ", player=" + player + ", age=" + age + ", region=" + region + ", position="
				+ position + ", playstyle=" + playstyle + ", price=" + price + ", injured=" + injured + ", details="
				+ details + "]";
	}
	
	
}
