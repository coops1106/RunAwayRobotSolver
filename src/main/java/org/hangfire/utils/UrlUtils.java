package org.hangfire.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UrlUtils {
	public static String getUrlResponse (String urlString) {
		BufferedReader bufferedReader = null;
		StringBuilder sb = null;
		try {
			URL url = new URL(urlString);		   
			URLConnection urlConnection = url.openConnection();
			bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			
			sb = new StringBuilder();
			 
	    	String line;
	    	while ((line = bufferedReader.readLine()) != null) {
	    		sb.append(line);
	    	}
	    	
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}