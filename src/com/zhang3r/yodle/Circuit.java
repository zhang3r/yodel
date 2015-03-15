package com.zhang3r.yodle;

import java.util.ArrayList;

public class Circuit {
	private ArrayList<Juggler> jugglers;
	private int[] circuitSkill;
	private String circuitName;
	

	public Circuit(String circuitName, int h, int e, int p) {
		circuitSkill = new int[] { h, e, p };
		this.circuitName = circuitName;
	}
	public Juggler getLowestScoreJuggler(){
		Juggler lowestJuggler = null;
		for(Juggler juggler: getJugglers()){
			if(lowestJuggler==null){
				lowestJuggler = juggler;
			}else if(lowestJuggler.getScoreFromCircuit(circuitName)>juggler.getScoreFromCircuit(circuitName)){
				lowestJuggler = juggler;
			}
		}
		return lowestJuggler;
	}

	public ArrayList<Juggler> getJugglers() {
		if (jugglers == null) {
			jugglers = new ArrayList<>();
		}
		return jugglers;
	}

	public int[] getCircuitSkill() {
		return circuitSkill;
	}

	public void setCircuitSkill(int[] circuitSkill) {
		this.circuitSkill = circuitSkill;
	}

	public String getCircuitName() {
		return circuitName;
	}

	public void setCircuitName(String circuitName) {
		this.circuitName = circuitName;
	}

}
