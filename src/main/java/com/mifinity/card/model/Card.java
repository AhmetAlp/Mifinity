package com.mifinity.card.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "card")
public class Card {
	private String number;
	private String holderName;
	private String expiryDate;
	
	public Card() {
		
	}

	public Card(String number, String holderName, String expiryDate) {
		this.number = number;
		this.holderName = holderName;
		this.expiryDate = expiryDate;
	}

	@Id
	@Column(name = "number", nullable = false)
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	@Column(name = "holdername", nullable = false)
	public String getHolderName() {
		return holderName;
	}
	public void setHolderName(String name) {
		this.holderName = name;
	}
	@Column(name = "expirydate", nullable = false)
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
		
}
