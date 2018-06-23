package com.mikelogovi.couchemetier;

public class CreateQuestion {
     private Quiz quiz = new Quiz();
     private QuizManager quizManager = new QuizManager();
     private  QuestionnaireMath math = new QuestionnaireMath();
     
     private QuestionnairePaysCapital paysCapital = new QuestionnairePaysCapital();
     private QuestionnaireCultureGenerale cultureGeneral = new QuestionnaireCultureGenerale();
     public CreateQuestion(TypeQuiz typeQuiz) {
    	 switch(typeQuiz) {
    	 case  MATH:quiz.setQuestionnaire(math.getQuestionReponse());
    	            quiz.setType(TypeQuiz.MATH);
    	            quizManager.createQuiz(quiz);
    	       break;
    	case  CULTURE_GENERAL:quiz.setQuestionnaire(cultureGeneral.getQuestionReponse());
                              quiz.setType(TypeQuiz.CULTURE_GENERAL);
    	                      quizManager.createQuiz(quiz);
               break; 
    	 case  PAYS_CAPITAL:quiz.setQuestionnaire(paysCapital.getQuestionReponse());
                             quiz.setType(TypeQuiz.PAYS_CAPITAL);    
    	                     quizManager.createQuiz(quiz);
               break; 
    	 }
     }
     public static void main(String[]args) {
         
    	 new CreateQuestion(TypeQuiz.PAYS_CAPITAL);
     }
}
