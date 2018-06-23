package com.mikelogovi.couchemetier;

public enum TypeQuiz {
    MATH("Math"),CULTURE_GENERAL("Culture General"),PAYS_CAPITAL("Pays Capital");
	private String value;
    TypeQuiz(String value){
    	this.value=value;
    }
    public String toString() {
    	return this.value;
    }
    public String baseName() {
    	return this.value.replaceFirst(" ","");
    }
}