package com.mycompany.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.mycompany.model.Passport;
import com.mycompany.service.PassportDetailsService;
import com.mycompany.service.PassportDetailsServiceImpl;
import com.mycompany.util.TesseractImageToTextProcessor;

public class App 
{
	private static TesseractImageToTextProcessor g_objImageToTextProcessor;
	private static PassportDetailsService g_objPassportDetailsService;
	private static Passport g_objPassport;  
	 
    public static void main( String[] args )
    {
    	
    	App app = new App();
    	g_objPassport = new Passport();
    	g_objPassportDetailsService = new PassportDetailsServiceImpl();
    	g_objImageToTextProcessor = new TesseractImageToTextProcessor();
    	
//        String output = g_objImageToTextProcessor.processImageFromFile("/Users/adityasrivastava/Documents/TesseractProject/my-app", "output1.tiff", "eng");
    	String output = null;
		try {
			InputStream io = new FileInputStream("/Users/adityasrivastava/Documents/TesseractProject/my-app/images/output1.tiff");
		
			output = g_objImageToTextProcessor.processImageFromMemory(io,"/Users/adityasrivastava/Documents/TesseractProject/my-app", "eng");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
        if (output != null) {
        	output = output.trim();
            String lines[] = output.split("\\r?\\n");
      
            String firstRow = lines[lines.length-2];
            String secondRow = lines[lines.length-1];
            
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
