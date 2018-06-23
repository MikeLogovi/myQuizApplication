package com.mikelogovi.couchemetier;

public class QuestionnaireCultureGenerale extends Questionnaire{
      String[] question= {"Quel est la fortune presidentielle de Donald Trump?\n\tA-1$ par mois\n\tB-475000 dollars par mois\n\tC-100000 dollars par mois",
    	 	              "Akku Yadav est un homme qui a violé plus de 200 femmes dans quel pays?\n\tA-Inde\n\tB-Nigeria\n\tC-Arabie Saoudite",
    	 	              "Le miel est le seul aliment sans date de péremption et qui peut se conserver à vie\n\tVrai ou Faux?",
    	 	              "La marijuana est légale en corée du nord et n'est pas considérée comme une drogue.\n\tVrai ou Faux?",
    	 	              "Comment s'appelle l'actuel roi de Belgique?\n\tA-Gustav de Belgique\n\tB-Jean de Belgique\n\tC-Phillipe de Belgique",
    	 	              "Combien de volume de Harry Potter avait été prévue selon son auteur?",
                          "Quel est le nom complet et réel de J.K.Rowling l'auteur de Harry Potter?\n\tA-Joanne Kennedy Rowling\n\tB-Joanne Rowling\n\tC-Joanne Karlson Rowling",
                          "Les ouvriers egyptiens qui ont construit les pyramides étaient payés comment?\n\tA-En bière,4l/jour\n\tB-En pépite d'or\n\tC-Par obtention d'une femme",
                          "Qui est le l'actuel premier ministre du Canada?\n\tA-Justin Trudeau\n\tB-Giuseppe Conte\n\tC-Edouard Phillipe",
                          "Entre 1815 et 1830,pendant la restauration,le drapeau francais avait quelle(s) couleur(s)\n\tA-Rouge\n\tB-Blanc\n\tC-Blanc,Rouge et Bleu"
      }; 
      String[] reponse= {"A","Inde","Vrai","Vrai","C","7","B","A","A","B"};
      public QuestionnaireCultureGenerale() {
    	 
    	for(int i=0;i< question.length;i++) {
    		 questionReponse.put(question[i],reponse[i]);
    	  }
      }
      
}
