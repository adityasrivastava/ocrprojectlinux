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
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.media.jai.RenderedImageAdapter;

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
    	
    	try {
			app.passportTest();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    }
    
    public void cardTest() {
    	
    	
    	
    }
    
    public void passportTest() throws Exception{
    	String outfile = "images/image1colorOut.tiff";
    
    	BufferedImage bi = ImageIO.read(new File("images/image1color.tiff"));
    	
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
