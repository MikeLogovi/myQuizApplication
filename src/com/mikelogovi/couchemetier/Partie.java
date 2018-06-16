package com.mikelogovi.couchemetier;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;

public class Partie {
    private ArrayList<Joueur> joueurs;
    private TypePartie typePartie;
   
	private Quiz quiz;
    private HashMap<Joueur,Integer> score;
    public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}
	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}
	public Quiz getQuiz() {
		return quiz;
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	public HashMap<Joueur, Integer> getScore() {
		return score;
	}
	public void setScore(HashMap<Joueur, Integer> score) {
		this.score = score;
	}
	 public TypePartie getTypePartie() {
			return typePartie;
		}
		public void setTypePartie(TypePartie typePartie) {
			this.typePartie = typePartie;
		}
	
    
}
