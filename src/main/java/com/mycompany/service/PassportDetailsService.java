package com.mycompany.service;

public interface PassportDetailsService {
	
	String PASSPORT_TYPE_REGEX = "^[A-Z]{1}";
	String ORGANIZATION_REGEX = "[A-Z\\d<\\s]{3}";
	String NAMES_REGEX = "[\\w\\d\\s]+";
	String PASSPORT_NUMBER_REGEX = "^[A-Z0-9]{8}";
	String NATIONALITY_REGEX = "[A-Z1\\s]{3}";
	String CHECK_DIGIT_REGEX = "[0-9O]{1}";
	String NAME_SEPERATOR_REGEX = "^[<\\w\\d]{2}";
	String PASS_AND_NAT_SEPERATOR_REGEX = "^[<\\w\\d]{1,2}";
	String SEPERATOR_REGEX = "^<+";
	String DATE_OF_BIRTH_REGEX = "[0-9]{6}";
	String DATE_OF_EXPIRY_REGEX = "[0-9D]{6}";
	String GENDER_REGEX = "[MF]{1}";
	
	void extractCheckDigit(String param_objInputString);
	boolean extractSeperator(String param_objInputString, String param_objSeperatorRegex);
	void setCurrentPositionIndex(int indexInitialization);
	char extractPassportType(String param_objInputString);
	char extractGender(String param_objInputString);
	String extractOrganization(String param_objInputString);
	String extractGivenName(String param_objInputString);
	String extractSurname(String param_objInputString);
	String extractPassportNumber(String param_objInputString);
	String extractNationality(String param_objInputString);
	String extractDateOfBirth(String param_objInputString);
	String extractExpiryDate(String param_objInputString);
	String extractMiddleName(String param_objInputString);
	

}
