package com.mycompany.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;

public class TesseractImageToTextProcessor {

	public String processImageFromMemory(InputStream param_objImageInputStream, String param_objTessDataRootPath, String param_objLanguage) throws Exception{
			
		byte[] v_arrImageInBytes;
		
		TessBaseAPI v_objTessBaseApi;
        PIX v_objPixImage = null;
        BytePointer v_objBytePointer = null;
        String v_objOutputText = null;
        v_objTessBaseApi = new TessBaseAPI();
       
	    try {
	    	
	        
	        if (v_objTessBaseApi.Init(param_objTessDataRootPath+"/tessdata", param_objLanguage) != 0) {
	            throw new RuntimeException("Could not initialize tesseract.");
	        }       
	        
	        v_objTessBaseApi.ReadConfigFile(param_objTessDataRootPath+"/tessdata/config/output.config");
	    	
			v_arrImageInBytes = inputStreamToBytes(param_objImageInputStream);
	    	v_objPixImage = lept.pixReadMem(v_arrImageInBytes, v_arrImageInBytes.length);
   	        v_objTessBaseApi.SetImage(v_objPixImage);
   	        v_objBytePointer = v_objTessBaseApi.GetUTF8Text();
   	        v_objOutputText = v_objBytePointer.getString("UTF-8");
   	        
            return v_objOutputText;

		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			v_objTessBaseApi.close();
		}

		return v_objOutputText;
		
	}
	
	public byte[] inputStreamToBytes(InputStream param_objInputStream) throws IOException{
		
		int v_iNextPointer;
		byte[] v_inputStreamByteArray;
		ByteArrayOutputStream v_objByteOutputStream = null;
		v_objByteOutputStream = new ByteArrayOutputStream();
		
		v_iNextPointer = param_objInputStream.read();
		
		while(v_iNextPointer > -1){
			v_objByteOutputStream.write(v_iNextPointer);
			v_iNextPointer = param_objInputStream.read();
		}
		
		v_inputStreamByteArray = v_objByteOutputStream.toByteArray();
		
		return v_inputStreamByteArray;
	}
	
	public String processImageFromFile(String param_objDirectoryPath, String param_objImageName, String param_objLanguageName) {
        
		   TessBaseAPI v_objTessBaseApi;
	        PIX v_objPixImage = null;
	        BytePointer v_objBytePointer = null;
	        String v_objOutputText = null;
	        
	        v_objTessBaseApi = new TessBaseAPI();

	        
	        if (v_objTessBaseApi.Init(param_objDirectoryPath+"/tessdata", param_objLanguageName) != 0) {
	            throw new RuntimeException("Could not initialize tesseract.");
	        }       
	        
	        v_objTessBaseApi.ReadConfigFile(param_objDirectoryPath+"/tessdata/config/output.config");

	        try {

	        	v_objPixImage = lept.pixRead(param_objDirectoryPath+"/"+param_objImageName);
	   	        v_objTessBaseApi.SetImage(v_objPixImage);
	   	        v_objTessBaseApi.SetSourceResolution(600);
//		       	v_objTessBaseApi.SetRectangle(arg0, arg1, arg2, arg3);
	   	        v_objBytePointer = v_objTessBaseApi.GetUTF8Text();
	   	        v_objOutputText = v_objBytePointer.getString("UTF-8");
	   	        
	   	 
	            return v_objOutputText;
	            
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
			return v_objOutputText;
	    }
	
	

}
