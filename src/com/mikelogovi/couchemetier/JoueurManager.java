package com.mikelogovi.couchemetier;
import java.sql.*;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
public class JoueurManager implements IJoueurManager {
    private PreparedStatement prepareStatement;
    private ResultSet resultSet;
	private BaseConnection db = new BaseConnection();
	private Joueur player = new Joueur();
	private PartieManager partieManager= new PartieManager();
	@Override
	public void createPlayer(Joueur joueur) {
	    String req="INSERT INTO Joueur(nom,motpass,dateCreated) VALUES(?,?,NOW())"; 	
	    try {
			prepareStatement=db.getConnexion().prepareStatement(req);
			prepareStatement.setString(1, joueur.getNom());
			prepareStatement.setString(2, joueur.getMotpass());
			//prepareStatement.setDate(3,Date.valueOf(LocalDate.now()));
			//prepareStatement.setDate(3,Date.from(Instant.now()));
			prepareStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

	@Override
	public Joueur readPlayer(Joueur joueur) {
		String req ="SELECT * FROM Joueur WHERE nom=? AND motpass=?";
		try {
			prepareStatement=db.getConnexion().prepareStatement(req);
			prepareStatement.setString(1, joueur.getNom());
			prepareStatement.setString(2, joueur.getMotpass());
			resultSet=prepareStatement.executeQuery();
			while(resultSet.next()){
				 player.setId(resultSet.getInt("id"));
				 player.setNom(resultSet.getString("nom"));
				 player.setMotpass(resultSet.getString("motpass"));
				 player.setPartie(partieManager.readPartie(joueur));
			}
			return player;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updatePlayer(Joueur joueur) {
		// TODO Auto-generated thod stub
		
	}

	@Override
	public void deletePlayer(Joueur joueur) {
		// TODO Auto-generated method stub
		
	}

}
