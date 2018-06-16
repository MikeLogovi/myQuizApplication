package com.mikelogovi.couchemetier;

import java.util.HashMap;

public class Quiz {
	 private int idQuiz; 
 	 private TypeQuiz type;
	 private HashMap<String,String> Questionnaire;
     private int score;
	 public TypeQuiz getType() {
		return type;
	 }
	 public void setType(TypeQuiz type) {
		this.type = type;
	 }
	 public HashMap<String, String> getQuestionnaire(TypeQuiz typeQuiz) {
		 
		 
		 return Questionnaire;
	 }
	 public int getIdQuiz() {
		return idQuiz;
	}
	public void setIdQuiz(int idQuiz) {
		this.idQuiz = idQuiz;
	}
	public void setQuestionnaire(HashMap<String, String> questionnaire) {
		Questionnaire = questionnaire;
	 }
     public int getScore() {
		return score;
	 }
     public void setScore(int score) {
		this.score = score;
	 }
}
