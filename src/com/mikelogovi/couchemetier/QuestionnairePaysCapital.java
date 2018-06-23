package com.mikelogovi.couchemetier;

public class QuestionnairePaysCapital extends Questionnaire {
    String[] pays= {"Togo","Algérie","Tunisie","Maroc","Albanie","Afghanistan","Allemagne","Portugal","Bresil","Thailand","Belgique","Burkina-Faso","Russie","Autriche","Bulgarie","Chypre","Croatie","Danemark","Espagne","Estonie","Finlande","France","Grèce","Hongrie","Irlande","Ghana","Afrique du sud"};
    String[] capital = {"Lomé","Alger","Tunis","Rabat","Tirana","Kaboul","Berlin","Lisbonne","Brasilia","Bangkok","Bruxelles","Ouagadougou","Moscou","Vienne","Sofia","Nicosie","zagreb","Copenhague","Madrid","Tallin","Helsinki","Paris","Athènes","Budapest","Dublin","Accra","pretoria"};
	String deb="Quel est la capital de ";
    public QuestionnairePaysCapital() {
    	super();
    	for(int i=0;i<pays.length;i++) {
    		questionReponse.put(deb+pays[i]+" ?",capital[i]);

    	}
    	
    }
}
