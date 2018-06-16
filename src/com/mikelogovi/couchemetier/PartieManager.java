package com.mikelogovi.couchemetier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class PartieManager implements IPartieManager{
	private PreparedStatement prepareStatement;
	private ResultSet resultSet;
    private BaseConnection db = new BaseConnection();
    private Quiz quiz;
    private Partie partie =new Partie();
    private HashMap<Joueur,Integer> scorePartie = new HashMap<Joueur,Integer>();
	@Override
	public void createPartie(Partie played) {
		String req="INSERT INTO partie(idJoueur,typePartie,typeQuiz,idQuiz,scoreJoueur,dateCreated) VALUES(?,?,?,?,NOW())";
		try {
			
			for(Joueur joueur : played.getJoueurs()) {
				prepareStatement= db.getConnexion().prepareStatement(req);
				prepareStatement.setInt(1, joueur.getId());
				prepareStatement.setString(2, played.getTypePartie().toString());
				prepareStatement.setString(3, played.getQuiz().getType().toString());
				prepareStatement.setInt(4, played.getQuiz().getIdQuiz());
				prepareStatement.setInt(5, joueur.getScorePartie());
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Partie >readPartie(Joueur joueur) {
			return JoueurParties(joueur);
	}

	@Override
	public void updatePartie() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePartie() {
		// TODO Auto-generated method stub
		
	}
    
	
	
	private ArrayList<Partie> JoueurParties(Joueur joueur) {
		String req = "SELECT * FROM partie,Joueur WHERE Joueur.id=partie.idJoueur";
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		ArrayList<Partie> parties = new ArrayList<Partie>();
    try {
		  prepareStatement = db.getConnexion().prepareStatement(req);
		  resultSet=prepareStatement.executeQuery();
		  if(resultSet!=null) {
			  joueurs.add(joueur);
			  
			  while(resultSet.next()) {
				      partie.setJoueurs(joueurs);
				      String typeQuiz;
				      typeQuiz=resultSet.getString("typeQuiz");
				      if(typeQuiz.equals(TypeQuiz.MATH.toString())) {
				            quiz.setType(TypeQuiz.MATH);  	  
				      }
				      else if(typeQuiz.equals(TypeQuiz.CULTURE_GENERAL.toString())) {
				    	   quiz.setType(TypeQuiz.CULTURE_GENERAL);
				      }
				      else if(typeQuiz.equals(TypeQuiz.PAYS_CAPITAL.toString())) {
				    	   quiz.setType(TypeQuiz.PAYS_CAPITAL);
				      } 
				      partie.setQuiz(quiz);
				      String typePartie=resultSet.getString("typePartie");
				      if(typePartie.equals(TypePartie.SOLO.toString())) {
				    	   partie.setTypePartie(TypePartie.SOLO);
				      }
				      else if(typePartie.equals(TypePartie.SOLO.toString())) {
				    	  partie.setTypePartie(TypePartie.EN_EQUIPE);
				      }
				      else if(typePartie.equals(TypePartie.DUEL.toString())) {
				    	  partie.setTypePartie(TypePartie.DUEL);
				      }
				      int score=resultSet.getInt("scoreJoueur");
				      scorePartie.put(joueur,score);
				      partie.setScore(scorePartie);
		              parties.add(partie);
			  }
			  return parties;
		  }
     }catch(SQLException e) {
		e.printStackTrace();
      }
    return null;
}
}
