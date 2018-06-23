package com.mikelogovi.couchemetier;

import java.util.ArrayList;
import java.util.HashMap;

abstract public class Questionnaire {
	protected HashMap<String,String>questionReponse=new HashMap<String,String>();
    //public ArrayList<String>questions;
    //public ArrayList<String>reponses;

	public HashMap<String, String> getQuestionReponse() {
		return questionReponse;
	}

	public void setQuestionReponse(HashMap<String, String> questionReponse) {
		this.questionReponse = questionReponse;
	}
}
