package com.opowerhomework;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

	//A simple POJO(Plain Old Java Object) to get and set the values of the marvel characters and the seed value

public class MarvelHeros {

	private int seed;

	private List<String> superheros;

	public int getSeed() {
		return seed;
	}
	public void setSeed(int seed) {
		this.seed = seed;
	}

	public List<String> getSuperheros() {
		return superheros;
	}
	public void setSuperheros(List<String> superheros) {
		this.superheros = superheros;
	}

}
