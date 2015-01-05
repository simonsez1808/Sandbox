package uk.co.practiceplan.mapping;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class TestMapQuestAccess {

	public static void main(String[] args) throws MalformedURLException, IOException {
		// TODO Auto-generated method stub
		String url = "http://open.mapquestapi.com/directions/v2/route";
			//	?key=YOUR_KEY_HERE&callback=renderAdvancedNarrative&outFormat=json&routeType=fastest&timeType=1&enhancedNarrative=false&shapeFormat=raw&generalize=0&locale=en_US&unit=m&from=38.8941,-77.07556&to=38.84458,-77.07823&drivingStyle=2&highwayEfficiency=21.0
//";
		String charset = "UTF-8";
		String key = "Fmjtd%7Cluurnuuz2g%2Caa%3Do5-9wrsqw";

		String from = "1 edward street, oswestry, shropshire";
		String to = "56 higher bebington road, bebington, wirral";
		// ...

		String query = String.format("?key=%s&from=%s&to=%s", 
		     key, 
		     URLEncoder.encode(from, charset),
		     URLEncoder.encode(to, charset));
		
		System.out.println(url + query);
		
		URLConnection connection = new URL(url + query).openConnection();
		connection.setRequestProperty("Accept-Charset", charset);
		// Get the response
		BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuffer sb = new StringBuffer();
		String line;
		while ((line = rd.readLine()) != null)
		{
		sb.append(line);
		}
		System.out.println("Response: " + sb);
		
	}

}
