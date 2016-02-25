package com.mycompany.handler;

import com.mycompany.model.Passport;
import com.mycompany.service.PassportDetailsService;
import com.mycompany.service.PassportDetailsServiceImpl;

public class PassportDetailsHandler {
	
	private PassportDetailsService g_objPassportDetailsService;
	private Passport g_objPassport;
	
	public Passport processPassportDetails(String param_objPassportInputString){
		
		String v_arrTesseractExtractedLines[] = null;
		String v_objUserDetailsRow;
		String v_objPassportDetailsRow;
        
		g_objPassportDetailsService = new PassportDetailsServiceImpl();
		g_objPassport = new Passport();
		
		if (param_objPassportInputString != null) {
			
			param_objPassportInputString = param_objPassportInputString.trim();
			v_arrTesseractExtractedLines = param_objPassportInputString.split("\\r?\\n");
      
            v_objUserDetailsRow = v_arrTesseractExtractedLines[v_arrTesseractExtractedLines.length-2].replaceAll(" ", "");
            v_objPassportDetailsRow = v_arrTesseractExtractedLines[v_arrTesseractExtractedLines.length-1].replaceAll(" ", "");
            
            System.out.println(v_objUserDetailsRow + " "+ v_objUserDetailsRow.length());
            System.out.println(v_objPassportDetailsRow + " "+ v_objPassportDetailsRow.length());
            
            // First Row for user details extraction
            char passportType = g_objPassportDetailsService.extractPassportType(v_objUserDetailsRow);
            String passportOrganization = g_objPassportDetailsService.extractOrganization(v_objUserDetailsRow);
            String passportSurname = g_objPassportDetailsService.extractSurname(v_objUserDetailsRow);
            String passportGivenName = g_objPassportDetailsService.extractGivenName(v_objUserDetailsRow);
            String passportMiddleName = g_objPassportDetailsService.extractMiddleName(v_objUserDetailsRow);
            
            // Set reset position
            g_objPassportDetailsService.setCurrentPositionIndex(0);
            // Second Row for passport details extraction
            String passportNumber = g_objPassportDetailsService.extractPassportNumber(v_objPassportDetailsRow);  
            String passportNationality = g_objPassportDetailsService.extractNationality(v_objPassportDetailsRow);
            String passportDOB = g_objPassportDetailsService.extractDateOfBirth(v_objPassportDetailsRow);
            char passportGender = g_objPassportDetailsService.extractGender(v_objPassportDetailsRow);
            String passportExpiry = g_objPassportDetailsService.extractExpiryDate(v_objPassportDetailsRow);
            
            
            g_objPassport.setTypeOfPassport(passportType);
            g_objPassport.setOrganization(passportOrganization);
            g_objPassport.setSurname(passportSurname);
            g_objPassport.setGivenName(passportGivenName);
            g_objPassport.setMiddlename(passportMiddleName);
            g_objPassport.setPassportNumber(passportNumber);
            g_objPassport.setNationality(passportNationality);
            g_objPassport.setDateOfBirth(passportDOB);
            g_objPassport.setExpirationDate(passportExpiry);
            g_objPassport.setGender(passportGender);
             
            System.out.println(g_objPassport.toString());

        }
		
		return g_objPassport;
	}

}
