package com.opowerhomework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

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

public class MarvelService {

		/*This method gets input.json as input and generates the json response based on the character from the service
		and then add those responses to the list */
	
	public void getResponseFromMarvel() {

		String publickey = generatekeys("marvel.public.api.key");
		String privatekey = generatekeys("marvel.private.api.key");

		//Setting publickey and privatekey values
		MarvelUrlGenerator marvelgen = new MarvelUrlGenerator(publickey,privatekey);
		
		//Converting JSON to Java Object
		ObjectMapper mapper = new ObjectMapper();
		List<String> response = new ArrayList();

		try {

			//File inputfile = new File(getClass().getResource("/com/opowerhomework/input.json").getFile());
			
			InputStream i=MarvelService.class.getResourceAsStream("/com/opowerhomework/input.json");
			
					
			//MarvelHeros superheros = mapper.readValue(inputfile, MarvelHeros.class);	
			MarvelHeros superheros = mapper.readValue(i, MarvelHeros.class);

			int seedvalue = superheros.getSeed();
			String marvresp = null;
			
			List<String> heronames=superheros.getSuperheros();

			for (Iterator<String> iter = heronames.iterator(); iter.hasNext(); ) {

				String element = iter.next();
				
				//generating the final URL based on the input character
				
				String final_url=marvelgen.URLGenerator(element);

				Client MarvelChar = ClientBuilder.newClient();

				WebTarget targetchar = MarvelChar.target(final_url);
				
				//getting response from the marvel service
				marvresp = targetchar.request(MediaType.APPLICATION_JSON).get(String.class);

				response.add(marvresp);
			}

			logic(seedvalue,response);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	//Generating the public and private keys from the properties file
	
	public String generatekeys(String key){

		Properties prop = new Properties();

			
			InputStream i=MarvelService.class.getResourceAsStream("/com/opowerhomework/marvelconfig.properties");
			

			try {

				prop.load(i);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
		return prop.getProperty(key);

	}

	/*This method retrives the word at the seed value in both the descriptions and 
	then these words are stored in a hashmap along with the heronames. The Hashmap 
	is the passed to the marvelWinner method in the MarvelWinner class to generate the final output*/
	
	public static void logic(int seedvalue,List<String> response){

		HashMap hm = new HashMap();
		for (Iterator<String> iter = response.iterator(); iter.hasNext(); ) {

			JSONParser parser = new JSONParser();
			try {
				JSONObject json = (JSONObject) parser.parse(iter.next());

				int i = json.size();
				JSONObject json_new=(JSONObject) json.get("data");
				int j = json_new.size();
				JSONArray jsonarr_1 = (JSONArray) json_new.get("results");

				JSONObject jsonobj_1 = (JSONObject)jsonarr_1.get(0);

				String heroname = (String)jsonobj_1.get("name");
				String herodesc = (String)jsonobj_1.get("description");
				herodesc.replace(',',' ');
				String words[] = herodesc.split(" ");
				
				String seedword = words[seedvalue-1];
				if(seedword.endsWith(",") || seedword.endsWith("."))
				{
					seedword = seedword.substring(0, seedword.length()-1);
					
				}
				
				hm.put(heroname, seedword);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		MarvelWinner marwin = new MarvelWinner();
		marwin.marvelWinner(hm);

	}

}
