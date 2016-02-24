package com.mycompany.model;

public class Passport {
	
	private int id;
	private char typeOfPassport;
	private char gender;
	private String organization;
	private String givenName;
	private String surname;
	private String middlename;
	private String passportNumber;
	private String nationality;
	private String dateOfBirth;
	private String expirationDate;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public char getTypeOfPassport() {
		return typeOfPassport;
	}
	public void setTypeOfPassport(char typeOfPassport) {
		this.typeOfPassport = typeOfPassport;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
	@Override
	public String toString() {
		return "Passport [id=" + id + ", typeOfPassport=" + typeOfPassport + ", gender=" + gender + ", organization="
				+ organization + ", givenName=" + givenName + ", surname=" + surname + ", middlename=" + middlename
				+ ", passportNumber=" + passportNumber + ", nationality=" + nationality + ", dateOfBirth=" + dateOfBirth
				+ ", expirationDate=" + expirationDate + "]";
	}
	

	

}
