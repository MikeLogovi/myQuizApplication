package com.mikelogovi.couchemetier;

import java.util.ArrayList;
import java.util.HashMap;

public class QuestionnaireMath extends Questionnaire {
     private String[] question= {"Qui a invente le théorème suivant:\nDans un triangle rectangle,le carré de l'hypothenus est égale à la somme \ndes carrés des deux autres cotés?","Que donne 1+2+3+4+5+6+7+.......?"};
     private String[] reponse= {"Pythagore","-1/12"};
     public QuestionnaireMath() {

    	 for(int i=0;i<this.question.length;i++) {
    		 questionReponse.put((String)this.question[i],(String)this.reponse[i]);
    		
    	 }
    	 
     }
}
