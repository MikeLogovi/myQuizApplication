package com.mikelogovi.couchemetier;

public interface IJoueurManager {
     public void createPlayer(Joueur joueur);
     public Joueur readPlayer(Joueur joueur);
     public void updatePlayer(Joueur joueur);
     public void deletePlayer(Joueur joueur);
}
