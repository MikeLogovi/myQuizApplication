package com.mikelogovi.coucheapplication;

public enum Gaming {
    NOUVELLE_PARTIE("NOUVELLE PARTIE"),CONTINUER_PARTIE("CONTINUER PARTIE"),CLASSEMENTS("CLASSEMENTS"),STATISTIQUES("STATISTIQUES"),QUITTER("QUITTER");
    private String value;
    Gaming(String value){
    	this.value =value;
    }
    public String toString() {
    	return this.value;
    }
}
