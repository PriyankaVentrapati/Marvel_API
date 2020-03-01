package com.opowerhomework;

public class MarvelHeroMain {

	/* The application execution starts here. From here we call the getResponseFromMarvel
	 * method in MarvelService class
	*/
	
	public static void main(String[] args) {
		MarvelService obj = new MarvelService();
		obj.getResponseFromMarvel();

	}

}
