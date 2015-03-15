package com.zhang3r.yodle;

import java.util.Dictionary;
import java.util.Hashtable;

public class Juggler {
	private String[] preference;
	private int[] skills;
	private Dictionary<String, Integer> score;
	private String name;

	public Juggler(String name, int h, int e, int p) {
		skills = new int[] { h, e, p };
		this.name = name;
		score = new Hashtable<>();
	}

	
	public Dictionary<String, Integer> getScore() {
		if(score==null){
			score = new Hashtable<>();
		}
		return score;
	}
	
	public int getScoreFromCircuit(String circuitName){
		return getScore().get(circuitName);
	}


	public void setScore(Dictionary<String, Integer> score) {
		this.score = score;
	}


	public String[] getPreference() {
		return preference;
	}

	public void setPreference(String[] preference) {
		this.preference = preference;
	}

	public int[] getSkills() {
		return skills;
	}

	public void setSkills(int[] skills) {
		this.skills = skills;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
