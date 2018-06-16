package com.mikelogovi.couchemetier;

public enum TypePartie {
    SOLO("SOLO"),EN_EQUIPE("EN EQUIPE"),DUEL("DUEL");
	private String value;
	TypePartie(String value){
		this.value=value;
	}
	public String toString() {
		return this.value;
	}
}
