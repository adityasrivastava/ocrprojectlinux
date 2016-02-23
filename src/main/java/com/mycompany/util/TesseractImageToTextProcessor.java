package com.mycompany.util;

import java.io.UnsupportedEncodingException;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;

public class TesseractImageToTextProcessor {

	   public static String process(String param_objDirectoryPath, String param_objImageName, String param_objLanguageName) {
	        TessBaseAPI v_objTessBaseApi;
	        PIX v_objPixImage = null;
	        BytePointer v_objBytePointer = null;
	        String v_objOutputText;
	        
	        v_objTessBaseApi = new TessBaseAPI();
	        
	        if (v_objTessBaseApi.Init(param_objDirectoryPath+"/tessdata", param_objLanguageName) != 0) {
	            throw new RuntimeException("Could not initialize tesseract.");
	        }       
	        
	        v_objTessBaseApi.ReadConfigFile(param_objDirectoryPath+"/tessdata/config/output.config");

	        try {
	        	
	        	v_objPixImage = lept.pixRead(param_objDirectoryPath+"/images/"+param_objImageName);
	   	        v_objTessBaseApi.SetImage(v_objPixImage);
	   	        v_objBytePointer = v_objTessBaseApi.GetUTF8Text();
	   	        v_objOutputText = v_objBytePointer.getString("UTF-8");
	           
	            return v_objOutputText;
	            
	        } catch (UnsupportedEncodingException e) {
	        	
	            throw new RuntimeException("charset", e);
	            
	        } finally {
	        	
	            if (v_objBytePointer != null) {
	            	v_objBytePointer.deallocate();
	            }
	            
	            if (v_objPixImage != null) {
	                lept.pixDestroy(v_objPixImage);
	            }
	            
	            if (v_objTessBaseApi != null) {
	            	v_objTessBaseApi.End();
	            }
	        }
	    }

}
