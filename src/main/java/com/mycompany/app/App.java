package com.mycompany.app;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;

import com.mycompany.model.Passport;
import com.mycompany.service.PassportDetailsService;
import com.mycompany.service.PassportDetailsServiceImpl;

public class App 
{
	private static PassportDetailsService g_objPassportDetailsService;
	private static Passport g_objPassport;
	
    public static void main( String[] args )
    {
    	
    	App app = new App();
    	g_objPassport = new Passport();
    	g_objPassportDetailsService = new PassportDetailsServiceImpl();
        String output = app.process("/Users/adityasrivastava/Documents/TesseractProject/my-app/image/output1.tiff");
        
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
            
            System.out.println();
            
//            String passportNationality = g_objPassportDetailsService.extractNationality(secondRow);
//            String passportDOB = g_objPassportDetailsService.extractDateOfBirth(secondRow);
//            String passportExpiry = g_objPassportDetailsService.extractExpiryDate(secondRow);
//            char passportGender = g_objPassportDetailsService.extractGender(secondRow);
            
            g_objPassport.setTypeOfPassport(passportType);
            g_objPassport.setOrganization(passportOrganization);
            g_objPassport.setSurname(passportSurname);
            g_objPassport.setGivenName(passportGivenName);
            g_objPassport.setPassportNumber(passportNumber);
//            g_objPassport.setNationality(passportNationality);
//            g_objPassport.setDateOfBirth(passportDOB);
//            g_objPassport.setExpirationDate(passportExpiry);
//            g_objPassport.setGender(passportGender);
            
            System.out.println(g_objPassport.toString());
            
//            passport = extractDetailsFromFirstPassportRow(firstRow, passport);
//            
            
            
           
//            passport = extractDetailsFromSecondPassportRow(secondRow, passport);
        }
    	
    	
    	
    	

    	
//        System.out.println(output);
    }
    
    public void func2(){
    	String b = "01221633<II ND9002063 F 1608285<<<<<<<<<<<<<<<6".replace(" ", "");
    	int index = 0;
    	String passportNumRegex = "^[A-Z0-9]+";
    	String nationalityRegex = "[A-Z\\s]{3}";
    	String checkDigitRegex = "[0-9]{1}";
    	String seperatorRegex = "[<]+";
    	String dateOfBirthRegex = "[0-9]{6}";
    	String dateOfExpiryRegex = "[0-9]{6}";
    	String genderRegex = "[MF]{1}";
    	
    	// Passport number
    	Pattern pattern = Pattern.compile(passportNumRegex);
    	Matcher matcher = pattern.matcher(b);
    	if(matcher.find()){
    		System.out.println(matcher.group(0));
    		index = matcher.end();
    	}
    	
    	// Check "<" seperator
    	
    	matcher = Pattern.compile(seperatorRegex).matcher(b);
    	matcher.region(index, b.length());
    	
    	if(matcher.find()){
    		index = matcher.end();
    	}
    	
    	// Check digit (ignore)
    	index++;
    	
    	// Nationality
    	matcher = Pattern.compile(nationalityRegex).matcher(b);
    	matcher.region(index, b.length());
    	if(matcher.find()){
    		System.out.println(matcher.group(0));
    	}
    	
    	// Check "<" seperator
    	
//    	matcher = Pattern.compile(seperatorRegex).matcher(b);
//    	matcher.region(index, b.length());
//    	
//    	if(matcher.find()){
//    		System.out.println(matcher.group(0));
//    		index = matcher.end();
//    	}
    	
    	// Date of Birth
    	matcher = Pattern.compile(dateOfBirthRegex).matcher(b);
    	matcher.region(index, b.length());
    	
    	if(matcher.find()){
    		System.out.println(matcher.group(0));
    		index = matcher.end();
    	}
    	
    	// Check digit (ignore)
    	index++;
    	
    	
    	// Gender
    	
    	matcher = Pattern.compile(genderRegex).matcher(b);
    	matcher.region(index, b.length());
    	
    	if(matcher.find()){
    		System.out.println(matcher.group(0));
    		index = matcher.end();
    	}
    	
    	// Date of Expiry
    	matcher = Pattern.compile(dateOfExpiryRegex).matcher(b);
    	matcher.region(index, b.length());
    	
    	if(matcher.find()){
    		System.out.println(matcher.group(0));
    		index = matcher.end();
    	}
    	
    	// Check digit (ignore)
    	index++;
    	
    	
    }
    
    public void func1(){
    	String regex = "^[A-Z]{1}";
    	String regex1 = "[A-Z<\\s]{3}";
    	String regex2 = "[A-Z<\\s]+";
    	String regex3 = "[A-Z\\s]+";
    	
    	String a = "PIINBSRI VAS TAVA<<EKTA<<<<<<<<<<<<<<I<<<V<<<I<<<";
    	Pattern pattern = Pattern.compile(regex);
    	Pattern pattern2 = Pattern.compile(regex1);
    	
    	Matcher match = pattern.matcher(a);
    	match.region(1, a.length());
    	// Passport type
    	System.out.println(match.find()+ " "+match.end());
    	System.out.println(match.group(0));
    	
    	System.out.println();
    	int index = match.end();

    	Matcher match2 = pattern2.matcher(a);
    	
    	match2.region(index,a.length());
    	// Organizations
    	if(match2.find()){
    		index = match2.end();
    		System.out.println(match2.end());
    		System.out.println(match2.group(0));
    	}
    	System.out.println();
    	// Names
    	Pattern pattern3 = Pattern.compile(regex2);
    	Matcher match3 = pattern3.matcher(a);
    	
    	match3.region(index, a.length());
    	String names = "";
    	if(match3.find()){
    		index = match3.end();
    		System.out.println(match3.end());
    		names = match3.group(0);
    		System.out.println(names);
    	}
    	
    	//Last Name
    	System.out.println();
    	Pattern pattern4 = Pattern.compile(regex3);
    	Matcher match4 = pattern4.matcher(names);
    	match4.region(0, names.length());
    	
    	String firstName = "";
    	if(match4.find()){
    		index = match4.end();
    		System.out.println(match4.end());
    		firstName = match4.group(0);
    		System.out.println(firstName);
    	}
    	
    	// Given names
    	Pattern pattern5 = Pattern.compile(regex3);
    	Matcher match5 = pattern5.matcher(names);
    	match5.region(index, names.length());
    	
    	if(match5.find()){
    		index = match5.end();
    		System.out.println(match5.end());
    		System.out.println(match5.group(0));
    	}
 
    	
    	
    }
    
    public Passport extractDetailsFromFirstPassportRow(String obj_FirstRowParam, Passport passport){
    	
		String givenname = "";
		String surname = "";
    	char[] firstRowArray = obj_FirstRowParam.toCharArray();
    	
    	passport.setTypeOfPassport(firstRowArray[0]);
    	
    	String builderOrg = ""+firstRowArray[2]+firstRowArray[3]+firstRowArray[4];
    	passport.setOrganization(builderOrg);
    

    	boolean surnameNameStartFlag = false; 
    	for(int offset=5; offset<firstRowArray.length; offset++){
    		
    		if(firstRowArray[offset] == '<'){
    			surnameNameStartFlag = true;
    			continue;
    		}
    		
    		if(surnameNameStartFlag == false){
    			givenname += firstRowArray[offset];
    		}else{
    			surname += firstRowArray[offset];
    		}
    		
    	}
    	
    	passport.setGivenName(givenname);
    	passport.setSurname(surname);
    	
    	System.out.println(passport.toString());
    	return passport;
    }

    
    public String process(String file) {
        TessBaseAPI api = new TessBaseAPI();

        if (api.Init("/Users/adityasrivastava/Documents/TesseractProject/my-app/tessdata", "eng") != 0) {
            throw new RuntimeException("Could not initialize tesseract.");
        }       
        api.ReadConfigFile("/Users/adityasrivastava/Documents/TesseractProject/my-app/tessdata/config/output.config");
        PIX image = null;
        BytePointer outText = null;
        try {
            image = lept.pixRead(file);
          
            api.SetImage(image);
            outText = api.GetUTF8Text();
            String string = outText.getString("UTF-8");
           
            return string;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("charset", e);
        } finally {
            if (outText != null) {
                outText.deallocate();
            }
            if (image != null) {
                lept.pixDestroy(image);
            }
            if (api != null) {
                api.End();
            }
        }
    }
}
