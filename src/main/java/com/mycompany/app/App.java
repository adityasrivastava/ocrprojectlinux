package com.mycompany.app;

import com.mycompany.model.Passport;
import com.mycompany.service.PassportDetailsService;
import com.mycompany.service.PassportDetailsServiceImpl;
import com.mycompany.util.TesseractImageToTextProcessor;

public class App 
{
	private static PassportDetailsService g_objPassportDetailsService;
	private static Passport g_objPassport;  
	 
    public static void main( String[] args )
    {
    	
    	App app = new App();
    	g_objPassport = new Passport();
    	g_objPassportDetailsService = new PassportDetailsServiceImpl();
        
        String output = TesseractImageToTextProcessor.process("/Users/adityasrivastava/Documents/TesseractProject/my-app", "output1.tiff", "eng");
        
        if (output != null) {
        	output = output.trim();
            String lines[] = output.split("\\r?\\n");
      
            String firstRow = lines[lines.length-2].replace(" ", "");
            String secondRow = lines[lines.length-1].replace(" ", "");
            
            System.out.println(firstRow);
            System.out.println(secondRow);
            
            char passportType = g_objPassportDetailsService.extractPassportType(firstRow);
            String passportOrganization = g_objPassportDetailsService.extractOrganization(firstRow);
            String passportSurname = g_objPassportDetailsService.extractSurname(firstRow);
            String passportGivenName = g_objPassportDetailsService.extractGivenName(firstRow);
            g_objPassportDetailsService.setCurrentPositionIndex(0);
            String passportNumber = g_objPassportDetailsService.extractPassportNumber(secondRow);  
            String passportNationality = g_objPassportDetailsService.extractNationality(secondRow);
            String passportDOB = g_objPassportDetailsService.extractDateOfBirth(secondRow);
            char passportGender = g_objPassportDetailsService.extractGender(secondRow);
            String passportExpiry = g_objPassportDetailsService.extractExpiryDate(secondRow);
            
            
            g_objPassport.setTypeOfPassport(passportType);
            g_objPassport.setOrganization(passportOrganization);
            g_objPassport.setSurname(passportSurname);
            g_objPassport.setGivenName(passportGivenName);
            g_objPassport.setPassportNumber(passportNumber);
            g_objPassport.setNationality(passportNationality);
            g_objPassport.setDateOfBirth(passportDOB);
            g_objPassport.setExpirationDate(passportExpiry);
            g_objPassport.setGender(passportGender);
             
            System.out.println(g_objPassport.toString());

        }

    }
}
