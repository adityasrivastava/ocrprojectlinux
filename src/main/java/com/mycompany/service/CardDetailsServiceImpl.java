package com.mycompany.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardDetailsServiceImpl implements CardDetailsService{
	
	private Matcher g_objMatcher;

	@Override
	public String extractName(String inputString) {
		String v_objName = null;
		
		g_objMatcher = Pattern.compile(NAME_REGEX).matcher(inputString);
		
		if(g_objMatcher.find()){
			v_objName = g_objMatcher.group(0);
		}
		return v_objName;
	}

	@Override
	public String extractTitle(String inputString) {
		String v_objTitle = null;
		
		g_objMatcher = Pattern.compile(TITLE_REGEX).matcher(inputString);
		
		if(g_objMatcher.find()){
			v_objTitle = g_objMatcher.group(0);
		}
		return v_objTitle;
	}

	@Override
	public String extractEmail(String inputString) {
		String v_objEmail = null;
		
		g_objMatcher = Pattern.compile(EMAIL_REGEX).matcher(inputString);
		
		if(g_objMatcher.find()){
			v_objEmail = g_objMatcher.group(0);
		}
		return v_objEmail;
	}

	@Override
	public String extractPhone(String inputString) {
		String v_objPhoneNumber = null;
		
		g_objMatcher = Pattern.compile(PHONE_REGEX).matcher(inputString);
		
		if(g_objMatcher.find()){
			v_objPhoneNumber = g_objMatcher.group(0);
		}

		return v_objPhoneNumber;
	}

	@Override
	public String extractAddress(String inputString) {
		String v_objAddress = null;
		
		g_objMatcher = Pattern.compile(ADDRESS_REGEX).matcher(inputString);
		
		if(g_objMatcher.find()){
			v_objAddress = g_objMatcher.group(0);
		}
		return v_objAddress;
	}

}
