package com.opowerhomework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.json.stream.JsonGenerationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class MarvelWinner {

	//This method compares both the words at the seed value and generates the output
	
	public static void marvelWinner(HashMap<String,String> hm){

		int i=0;

		for(Map.Entry<String, String> entry1: hm.entrySet()) {
			if(i==0){

				String key1 = entry1.getKey();
				String value1 = entry1.getValue();

				for(Map.Entry<String, String> entry2: hm.entrySet()) {
					if(i==0){

						String key2 = entry2.getKey();
						if(key1!=key2){


							String value2 = entry2.getValue();
							
							System.out.println("The word at the seed value for " +key1+ " is : " +value1+ "\n");
							System.out.println("The word at the seed value for " +key2+ " is : " +value2+ "\n");
							
							if(value1 == null && value2 != null){System.out.println(key2 + " is the winner");}

							else if(value2 == null && value1 != null){System.out.println(key1 + " is the winner"); }

							else if(value1 == null && value2 == null){System.out.println("There is no winner");}

							else if((value1.equalsIgnoreCase("gamma") || value1.equalsIgnoreCase("radioactive")) && (value2.equalsIgnoreCase("gamma") || value2.equalsIgnoreCase("radioactive")))
							{
								System.out.println("Both " +key1+ " and "+ key2+ " are winners");
								

							}

							else if(value1.equalsIgnoreCase("gamma") || value1.equalsIgnoreCase("radioactive")){System.out.println(key1 + " is the winner"); }

							else if(value2.equalsIgnoreCase("gamma") || value2.equalsIgnoreCase("radioactive")){System.out.println(key2 + " is the winner");}

							else if(value1.length() > value2.length()){System.out.println(key1 + " is the winner");}
							else if(value1.length() < value2.length()){System.out.println(key2 + " is the winner");}
							else if(value1.length() == value2.length()){System.out.println("Both are winners");}

							i=1;

						}
					}}
			}}

	}

}
