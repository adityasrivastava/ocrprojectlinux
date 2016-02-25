package com.mycompany.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.core.Info;

public class ImageProcessingUtil {
	
	private static String IMAGE_FORMAT_REGEX = "^.*\\.(tiff|tif)$";
	
	private static Matcher g_objMatcher;
	private static ConvertCmd g_objConvertCmd;
	private static IMOperation g_objImoOperations;
	
	public static void processImageFileForDetailExtraction(String param_objImageName, String param_objOutputImageName) throws Exception{
		
		// Check file format or else throw exception
		
		if(!checkImageFileNameFormat(param_objImageName)){
			throw new Exception("File Format Not supported !!!");
		}
		
		g_objConvertCmd = new ConvertCmd();
		g_objConvertCmd.setSearchPath("/opt/ImageMagick/bin");
		g_objImoOperations = new IMOperation();
    	g_objImoOperations.addImage(); 
    	g_objImoOperations.resize(640,480);
    	g_objImoOperations.addImage();
    	
    	try {
    		g_objConvertCmd.run(g_objImoOperations, param_objImageName, param_objOutputImageName);
		} catch (IOException e1) {
			
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			
			e1.printStackTrace();
		} catch (IM4JavaException e1) {
		
			e1.printStackTrace();
		}
	}
	
	public static void processImageStreamForDetailExtraction(BufferedImage param_objImageStream, String param_objOutputImageName) throws Exception{
		
		// Check file format or else throw exception
		
		
		g_objConvertCmd = new ConvertCmd();
		g_objConvertCmd.setSearchPath("/opt/ImageMagick/bin");
		g_objImoOperations = new IMOperation();
    	g_objImoOperations.addImage(); 
    	
//    	g_objImoOperations.resize(640,480);
    	if(param_objImageStream.getWidth() > 800 && param_objImageStream.getHeight() > 600){
    		g_objImoOperations.resize(800,600);
    	}
    
    	g_objImoOperations.addImage();

    	try {
    		g_objConvertCmd.run(g_objImoOperations, param_objImageStream , param_objOutputImageName);
		} catch (IOException e1) {
			
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			
			e1.printStackTrace();
		} catch (IM4JavaException e1) {
		
			e1.printStackTrace();
		}
	}
	
	public static boolean checkImageFileNameFormat(String param_objImageName){
		
		g_objMatcher = Pattern.compile(IMAGE_FORMAT_REGEX).matcher(param_objImageName);
		
		if(g_objMatcher.find()){
			return true;
		}
		
		return false;
		
	}

}
