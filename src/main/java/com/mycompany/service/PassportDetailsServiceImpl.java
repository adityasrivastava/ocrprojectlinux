package com.mycompany.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassportDetailsServiceImpl implements PassportDetailsService {

	private Matcher g_objMatcher;
	private int g_iCurrentPositionIndex = 0;

	public PassportDetailsServiceImpl() {
		g_iCurrentPositionIndex = 0;
	}

	public void setCurrentPositionIndex(int currentPosition) {
		g_iCurrentPositionIndex = currentPosition;
	}

	@Override
	public char extractPassportType(String param_objInputString) {
		char v_objPassportType = 0;
//		this.extractSeperator(param_objInputString);
		Matcher match = Pattern.compile(PASSPORT_TYPE_REGEX)
				.matcher(param_objInputString);
		match.region(g_iCurrentPositionIndex, param_objInputString.length());

		if (match.find()) {
			v_objPassportType = match.group(0).charAt(0);
			g_iCurrentPositionIndex = match.end();
		}
		
		return v_objPassportType;

	}

	@Override
	public String extractOrganization(String param_objInputString) {
		String v_objOrganization = null;
		this.extractSeperator(param_objInputString);
		Matcher match = Pattern.compile(ORGANIZATION_REGEX)
				.matcher(param_objInputString);
		match.region(g_iCurrentPositionIndex, param_objInputString.length());

		if (match.find()) {
			v_objOrganization = match.group(0);
			g_iCurrentPositionIndex = match.end();
		}

		return v_objOrganization;

	}

	@Override
	public String extractGivenName(String param_objInputString) {
		String v_objGivenName = null;
		this.extractSeperator(param_objInputString);
		Matcher match = Pattern.compile(NAMES_REGEX)
				.matcher(param_objInputString);
		match.region(g_iCurrentPositionIndex, param_objInputString.length());

		if (match.find()) {
			v_objGivenName = match.group(0);
			v_objGivenName.replaceAll(" ", "");
			g_iCurrentPositionIndex = match.end();
		}

		return v_objGivenName;

	}

	@Override
	public String extractSurname(String param_objInputString) {
		String v_objSurname = null;
		this.extractSeperator(param_objInputString);
		Matcher match = Pattern.compile(NAMES_REGEX)
				.matcher(param_objInputString);
		match.region(g_iCurrentPositionIndex, param_objInputString.length());

		if (match.find()) {
			v_objSurname = match.group(0);
			g_iCurrentPositionIndex = match.end();
		}

		return v_objSurname;

	}

	@Override
	public String extractPassportNumber(String param_objInputString) {

		String v_objPassportNumber = null;
		this.extractSeperator(param_objInputString);
		Matcher match = Pattern.compile(PASSPORT_NUMBER_REGEX)
				.matcher(param_objInputString);
		match.region(g_iCurrentPositionIndex, param_objInputString.length());

		if (match.find()) {
			v_objPassportNumber = match.group(0);
			g_iCurrentPositionIndex = match.end();
		}

		return v_objPassportNumber;

	}

	@Override
	public String extractNationality(String param_objInputString) {

		String v_objNationality = null;
		this.extractSeperator(param_objInputString);
		this.extractCheckDigit(param_objInputString);
		Matcher match = Pattern.compile(NATIONALITY_REGEX)
				.matcher(param_objInputString);
		match.region(g_iCurrentPositionIndex, param_objInputString.length());

		if (match.find()) {
			v_objNationality = match.group(0);
			v_objNationality = v_objNationality.replace("1", "I");
			g_iCurrentPositionIndex = match.end();
		}

		return v_objNationality;

	}

	@Override
	public String extractDateOfBirth(String param_objInputString) {

		String v_objDOB = null;
		this.extractSeperator(param_objInputString);
		Matcher match = Pattern.compile(DATE_OF_BIRTH_REGEX)
				.matcher(param_objInputString);
		match.region(g_iCurrentPositionIndex, param_objInputString.length());

		if (match.find()) {
			v_objDOB = match.group(0);
			g_iCurrentPositionIndex = match.end();
		}

		return v_objDOB;

	}

	@Override
	public String extractExpiryDate(String param_objInputString) {

		String v_objExpiryDate = null;
		this.extractSeperator(param_objInputString);
//		this.extractCheckDigit(param_objInputString);
		Matcher match = Pattern.compile(DATE_OF_EXPIRY_REGEX)
				.matcher(param_objInputString);
		match.region(g_iCurrentPositionIndex, param_objInputString.length());

		if (match.find()) {
			v_objExpiryDate = match.group(0);
			v_objExpiryDate = v_objExpiryDate.replace("D", "0").replace("O", "0");
			g_iCurrentPositionIndex = match.end();
		}

		return v_objExpiryDate;

	}

	@Override
	public char extractGender(String param_objInputString) {

		char v_objGender = 0;
		this.extractSeperator(param_objInputString);
		Matcher match = Pattern.compile(GENDER_REGEX)
				.matcher(param_objInputString);
		match.region(g_iCurrentPositionIndex, param_objInputString.length());

		if (match.find()) {
			v_objGender = match.group(0).charAt(0);
			g_iCurrentPositionIndex = match.end();
		}

		return v_objGender;

	}

	@Override
	public void extractCheckDigit(String param_objInputString) {

		String v_objCheckDigit = null;

		Matcher match = Pattern.compile(CHECK_DIGIT_REGEX)
				.matcher(param_objInputString);
		match.region(g_iCurrentPositionIndex, param_objInputString.length());

		if (match.find()) {
			v_objCheckDigit = match.group(0);
			g_iCurrentPositionIndex = match.end();
		}

	}

	@Override
	public void extractSeperator(String param_objInputString) {

		String v_objSerpator = null;

		Matcher match = Pattern.compile(SEPERATOR_REGEX)
				.matcher(param_objInputString);
		match.region(g_iCurrentPositionIndex, param_objInputString.length());

		if (match.find()) {
			v_objSerpator = match.group(0);
			g_iCurrentPositionIndex = match.end();
		}


	}

}
