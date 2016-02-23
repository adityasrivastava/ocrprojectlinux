package com.mycompany.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;

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
    	
    	ConvertCmd cmd = new ConvertCmd();
    	cmd.setSearchPath("/opt/ImageMagick/bin");
    	IMOperation imo = new IMOperation();
    	imo.addImage();
//    	imo.resize(640,480);
//    	imo.type("Grayscale");
    	imo.addImage();
    	
    	try {
    		
    		cmd.run(imo, "images/image1color.tiff", "images/image1colorOut.tiff");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IM4JavaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	
    	String output = null;
    	g_objPassport = new Passport();
    	g_objPassportDetailsService = new PassportDetailsServiceImpl();
    	g_objImageToTextProcessor = new TesseractImageToTextProcessor();
    	
//        String output = g_objImageToTextProcessor.processImageFromFile("/Users/adityasrivastava/Documents/TesseractProject/my-app", "output1.tiff", "eng");
    	
		try {
			InputStream io = new FileInputStream("/Users/adityasrivastava/Documents/TesseractProject/my-app/images/image1colorOut.tiff");
			output = g_objImageToTextProcessor.processImageFromMemory(io,"/Users/adityasrivastava/Documents/TesseractProject/my-app", "eng");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
        if (output != null) {
        	output = output.trim();
            String lines[] = output.split("\\r?\\n");
      
            String firstRow = lines[lines.length-2].replace(" ", "");
            String secondRow = lines[lines.length-1].replace(" ", "");
            
            System.out.println(firstRow + " "+ firstRow.length());
            System.out.println(secondRow + " "+ secondRow.length());
            
            char passportType = g_objPassportDetailsService.extractPassportType(firstRow);
            String passportOrganization = g_objPassportDetailsService.extractOrganization(firstRow);
            String passportSurname = g_objPassportDetailsService.extractSurname(firstRow);
            String passportGivenName = g_objPassportDetailsService.extractGivenName(firstRow);
            // Set reset position
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
