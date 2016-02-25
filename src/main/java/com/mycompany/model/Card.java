package com.mycompany.model;

public class Card {
	
	private int id;
	private String cardName;
	private String name;
	private String profile;
	private String contactNumber;
	private String emailAddress;
	private String primaryAddress;
	private String secondaryAddress;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPrimaryAddress() {
		return primaryAddress;
	}
	public void setPrimaryAddress(String primaryAddress) {
		this.primaryAddress = primaryAddress;
	}
	public String getSecondaryAddress() {
		return secondaryAddress;
	}
	public void setSecondaryAddress(String secondaryAddress) {
		this.secondaryAddress = secondaryAddress;
	}
	@Override
	public String toString() {
		return "Card [id=" + id + ", cardName=" + cardName + ", name=" + name + ", profile=" + profile
				+ ", contactNumber=" + contactNumber + ", emailAddress=" + emailAddress + ", primaryAddress="
				+ primaryAddress + ", secondaryAddress=" + secondaryAddress + "]";
	}
	
	

}
