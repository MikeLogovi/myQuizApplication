package com.mikelogovi.couchemetier;
import java.sql.*;
import java.util.HashMap;
public class QuizManager {
    private BaseConnection db = new BaseConnection();
    private ResultSet rs;
    private PreparedStatement preparedStatement;
    private HashMap<String,String>questionnaire=new HashMap<String,String>();
    public void createQuiz(Quiz quiz) {
    	String req="INSERT INTO quiz"+quiz.getType().baseName()+"(question,reponse) VALUES(?,?)";
        try {
			
			for(String question:quiz.getQuestionnaire(quiz.getType()).keySet()) {
				preparedStatement=db.getConnexion().prepareStatement(req);
				preparedStatement.setString(1,question);
				preparedStatement.setString(2,quiz.getQuestionnaire(quiz.getType()).get(question));
				preparedStatement.execute();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    public HashMap<String,String> readQuestionnaire(TypeQuiz typeQuiz) {
    	String req="SELECT * FROM quiz"+typeQuiz.baseName();
    	try{
    		preparedStatement=db.getConnexion().prepareStatement(req);
    		rs=preparedStatement.executeQuery(req);
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	try {
			while(rs.next()) {
				questionnaire.put(rs.getString("question"), rs.getString("reponse"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return questionnaire;
    }
    private void updateQuiz(Quiz quiz) {
    	
    }
    private void deleteQuiz(Quiz quiz) {
    	
    }
}
