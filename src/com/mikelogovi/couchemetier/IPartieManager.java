package com.mikelogovi.couchemetier;

import java.util.ArrayList;

public interface IPartieManager {
     public void createPartie(Partie played);
     public ArrayList<Partie> readPartie(Joueur joueur);
     public void updatePartie();
     public void deletePartie();
}
