package com.mikelogovi.couchemetier;

import java.util.ArrayList;

public class Joueur {
	private Integer id;
	private String nom;
    private String motpass;
    private ArrayList<Partie> partie;
    private int scorePartie;
    public int getScorePartie() {
		return scorePartie;
	}
	public void setScorePartie(int scorePartie) {
		this.scorePartie = scorePartie;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
    public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getMotpass() {
		return motpass;
	}
	public void setMotpass(String motpass) {
		this.motpass = motpass;
	}
	public ArrayList<Partie> getPartie() {
		return partie;
	}
	public void setPartie(ArrayList<Partie> partie) {
		this.partie = partie;
	}
}
