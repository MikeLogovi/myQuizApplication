package com.mikelogovi.coucheapplication;

public enum AfterGaming {
   REJOUER("REJOUER"),MENU_PRINCIPAL("MENU PRINCIPAL"),QUITTER("QUITTER");
	private String value;
	AfterGaming(String value){
		this.value=value;
	}
	public String toString() {
		return this.value;
	}
}
