package com.mycompany.app;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.media.jai.RenderedImageAdapter;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.lept.BOX;
import org.bytedeco.javacpp.lept.BOXA;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.lept.PIXA;
import org.bytedeco.javacpp.tesseract.ChoiceIterator;
import org.bytedeco.javacpp.tesseract.PageIterator;
import org.bytedeco.javacpp.tesseract.ResultIterator;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;
//import org.bytedeco.javacpp.tesseract.TessBaseAPI;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.core.Stream2BufferedImage;

import com.mycompany.handler.PassportDetailsHandler;
import com.mycompany.model.Passport;
import com.mycompany.service.PassportDetailsService;
import com.mycompany.service.PassportDetailsServiceImpl;
import com.mycompany.util.ImageProcessingUtil;
import com.mycompany.util.TesseractImageToTextProcessor;
import com.sun.media.jai.codec.FileSeekableStream;
import com.sun.media.jai.codec.TIFFDecodeParam;

public class App 
{
	private static TesseractImageToTextProcessor g_objImageToTextProcessor;
	private static Passport g_objPassport;   
	
	
	   
    public static void main( String[] args ) 
    {

    	App app = new App();  
    	
    	app.cardTest();
    	
//    	try {
//			app.passportTest();
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
    }
    
    public void cardTest() {
    	
    	String param_objDirectoryPath = "/Users/adityasrivastava/Documents/TesseractProject/my-app/images/card";
    	String param_objImageName = "imag3out.jpg";
    	String param_objLanguageName = "eng";
    	
		   	TessBaseAPI v_objTessBaseApi;
	        PIX v_objPixImage = null;
	        BytePointer v_objBytePointer = null;
	        String v_objOutputText = null;
	        
	        v_objTessBaseApi = new TessBaseAPI();

	        if (v_objTessBaseApi.Init("."+"/tessdata", param_objLanguageName) != 0) {
	            throw new RuntimeException("Could not initialize tesseract.");
	        }       
	        
	        v_objTessBaseApi.ReadConfigFile("."+"/tessdata/config/output.config");

	        try {

	        	v_objPixImage = lept.pixRead(param_objDirectoryPath+"/"+param_objImageName);
	   	        v_objTessBaseApi.SetImage(v_objPixImage);
	   	        v_objTessBaseApi.SetPageSegMode(6);
	   	        
	   	        int[] a = null;
	   	        PIXA pixa = null;
	   	        BOXA boxa = v_objTessBaseApi.GetComponentImages(2, true, pixa, a);
	   	   
//	   	        System.out.println(boxa.sizeof());
	   	       
	   	        for(int i = 0 ; i < boxa.sizeof(); i++){
	   	        	BOX box = boxa.box(i);
	   	        	v_objTessBaseApi.SetRectangle(box.x(), box.y(), box.h(), box.w());
	   	        	
	   	        	BytePointer bip = v_objTessBaseApi.GetUTF8Text();
	   	        	
	   	        	System.out.println(bip.getString("UTF-8"));
	   	        	
//	   	        	System.out.println(v_objTessBaseApi.MeanTextConf());
	   	        	
	   	        	
	   	        }
	   	        
	   	        
	   	        
	   	        
	   	        int byteP = v_objTessBaseApi.MeanTextConf();
	   	       
	   	       
	   	       
	   	       
	   	      
	   	       System.out.println(byteP);
	   	        
//	   	        v_objTessBaseApi.SetSourceResolution(600);
//		       	v_objTessBaseApi.SetRectangle(arg0, arg1, arg2, arg3);
	   	        v_objBytePointer = v_objTessBaseApi.GetUTF8Text();
	   	        v_objOutputText = v_objBytePointer.getString("UTF-8");
	   	        
	   	 
	            System.out.println("HIIII"+v_objOutputText);;
	            
	        } catch (UnsupportedEncodingException e) {
	        	
	            throw new RuntimeException("charset", e);
	            
	        }catch (IOException e){
	        	
	        }
	        finally {

	            if (v_objBytePointer != null) {
	            	v_objBytePointer.deallocate();
	            }
	            
	            if (v_objPixImage != null) {
	                lept.pixDestroy(v_objPixImage);
	            }
	            
	            if (v_objTessBaseApi != null) {
	            	v_objTessBaseApi.End();
	            }
	            
//	            Path path = Paths.get(param_objDirectoryPath+"/images/"+param_objImageName);
//	            try {
//					Files.delete(path);
//				} catch (IOException e) {
//					
//					e.printStackTrace();
//				}
	        }
    	
    }
    
    public void passportTest() throws Exception{
    	String outfile = "images/image1colorOut.tiff";
    
    	BufferedImage bi = ImageIO.read(new File("images/passport.jpg"));
    	
    	// Process Images to output file
		ImageProcessingUtil.processImageStreamForDetailExtraction(bi, outfile);

    	String output = null;
    	g_objPassport = new Passport();
    	g_objImageToTextProcessor = new TesseractImageToTextProcessor();
   
    	// Process Image from file
    	output = g_objImageToTextProcessor.processImageFromFile(".", outfile, "eng");
    	
    	// Extract details
    	PassportDetailsHandler v_objHandler = new PassportDetailsHandler();
    	v_objHandler.processPassportDetails(output);
    
    	// Buffer stream access
//		try {
//			InputStream io = new FileInputStream("/Users/adityasrivastava/Documents/TesseractProject/my-app/images/file1.tiff");
//			output = g_objImageToTextProcessor.processImageFromMemory(io,"/Users/adityasrivastava/Documents/TesseractProject/my-app", "eng");
//			
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
    }
}
