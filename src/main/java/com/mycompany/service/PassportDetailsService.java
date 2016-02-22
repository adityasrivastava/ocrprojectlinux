package com.mycompany.service;

public interface PassportDetailsService {
	
	String PASSPORT_TYPE_REGEX = "^[A-Z]{1}";
	String ORGANIZATION_REGEX = "[A-Z<\\s]{3}";
	String NAMES_REGEX = "[A-Z\\s]+";
	String PASSPORT_NUMBER_REGEX = "^[A-Z0-9]+";
	String NATIONALITY_REGEX = "[A-Z\\s]{3}";
	String CHECK_DIGIT_REGEX = "[0-9]{1}";
	String SEPERATOR_REGEX = "^<+";
	String DATE_OF_BIRTH_REGEX = "[0-9]{6}";
	String DATE_OF_EXPIRY_REGEX = "[0-9]{6}";
	String GENDER_REGEX = "[MF]{1}";
	
	char extractPassportType(String param_objInputString);
	char extractGender(String param_objInputString);
	String extractOrganization(String param_objInputString);
	String extractGivenName(String param_objInputString);
	String extractSurname(String param_objInputString);
	String extractPassportNumber(String param_objInputString);
	String extractNationality(String param_objInputString);
	String extractDateOfBirth(String param_objInputString);
	String extractExpiryDate(String param_objInputString);
	String extractCheckDigit(String param_objInputString);
	String extractSeperator(String param_objInputString);
	void setCurrentPositionIndex(int indexInitialization);

}
