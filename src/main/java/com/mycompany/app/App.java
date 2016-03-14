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
//import javax.media.jai.RenderedImageAdapter;



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
import com.mycompany.service.CardDetailsService;
import com.mycompany.service.CardDetailsServiceImpl;
import com.mycompany.service.PassportDetailsService;
import com.mycompany.service.PassportDetailsServiceImpl;
import com.mycompany.util.ImageProcessingUtil;
import com.mycompany.util.TesseractImageToTextProcessor;
//import com.sun.media.jai.codec.FileSeekableStream;
//import com.sun.media.jai.codec.TIFFDecodeParam;

public class App 
{
	private static TesseractImageToTextProcessor g_objImageToTextProcessor;
	private static Passport g_objPassport;   
	String emailRegex = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
	String phoneRegex  = "\\(?[0-9]{3}\\)?[-. ]?[0-9]{3}[-. ]?[0-9]{4}";
	String addressRegex = "[0-9]{1,6} [A-Za-z]+";
	String addressRegex2 = "[A-Za-z]+, [A-Za-z]+ [0-9]{5}";
	
	
	
	   
    public static void main( String[] args ) 
    {

    	App app = new App();  
    	
    	
    	ConvertCmd cmd = new ConvertCmd();
    	
    	IMOperation operation = new IMOperation();
    	
    	operation.addImage("images/image1.jpg");
    	operation.density(400);
    	operation.colorspace("gray");
    	operation.blackThreshold(20.0, true);
    	operation.gaussianBlur(2.0, 10.0);
    	operation.addImage();

    	
    	
    	try {
//        	cmd.run(operation,"images/testimage.jpg");
    		cmd.run(operation, "images/image1colorOut.tiff");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IM4JavaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
//    	app.cardTest();
    	
    	app.passportTest();
    	
//    	try {
//			app.passportTest();
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
    }
    
    public void cardTest() {
    	
    	String param_objDirectoryPath = "/home/adi/TesseractTraining/tesseractProject/ocrprojectlinux/images";
    	String param_objImageName = "testimage.jpg";
    	String param_objLanguageName = "eng";
    	
		   	TessBaseAPI v_objTessBaseApi;
	        PIX v_objPixImage = null;
	        BytePointer v_objBytePointer = null;
	        String v_objOutputText = null;
	        
	        v_objTessBaseApi = new TessBaseAPI();

	        if (v_objTessBaseApi.Init("."+"/tessdata", param_objLanguageName) != 0) {
	            throw new RuntimeException("Could not initialize tesseract.");
	        }       
	        
	        v_objTessBaseApi.ReadConfigFile("."+"/tessdata/config/letter.config");

	        try {

	        	v_objPixImage = lept.pixRead(param_objDirectoryPath+"/"+param_objImageName);
	   	        v_objTessBaseApi.SetImage(v_objPixImage);
	   	        v_objTessBaseApi.SetPageSegMode(6);

	   	        
//	   	        v_objTessBaseApi.SetSourceResolution(600);
//		       	v_objTessBaseApi.SetRectangle(arg0, arg1, arg2, arg3);
	   	        v_objBytePointer = v_objTessBaseApi.GetUTF8Text();
	   	        v_objOutputText = v_objBytePointer.getString("UTF-8");
	   	        
	   	 
	            System.out.println(v_objOutputText);
	            
	            CardDetailsService cardService = new CardDetailsServiceImpl();
//	            String output = cardService.extractPhone(v_objOutputText);
//	            String output3 = cardService.extractAddress(v_objOutputText);
//	            String output2 = cardService.extractEmail(v_objOutputText);
//	            String output4 = cardService.extractName(v_objOutputText);
	            String output5 = cardService.extractTitle(v_objOutputText);
	            
//	            System.out.println(output);
//	            System.out.println(output2);
//	            System.out.println(output3);
//	            System.out.println(output4);
	            
	            System.out.println(output5);
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
