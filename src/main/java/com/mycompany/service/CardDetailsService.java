package com.mycompany.service;

public interface CardDetailsService {
	
	String NAME_REGEX = "[\\w]+([\\w]+|\\.)(?:\\s+[\\w]([\\w]+|\\.))*(?:\\s+[\\w][\\w\\-]+){0,2}\\s+[\\w]([\\w]+|\\.)";
	String TITLE_REGEX = "\\n[\\w\\s&]+[\\w]+\\n";
	String EMAIL_REGEX = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
	String PHONE_REGEX  = "\\(?[0-9]{3}\\)?[-. ]?[0-9]{3}[-. ]?[0-9]{4}";
	String ADDRESS_REGEX = "([\\d])*[A-z\\s\\.]+(\\s)*\\d{6}\\s";
	
	
	public String extractName(String inputString);
	public String extractTitle(String inputString);
	public String extractEmail(String inputString);
	public String extractPhone(String inputString);
	public String extractAddress(String inputString);

}
