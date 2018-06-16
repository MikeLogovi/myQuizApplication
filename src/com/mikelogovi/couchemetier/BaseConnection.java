package com.mikelogovi.couchemetier;
import java.sql.*;
public class BaseConnection {
   private static String driver="com.msql.jdbc.Driver";
   private static String url="jdbc:mysql://localhost/quizBase?user=root&password=";
   private Connection connexion;
   private Statement statement;
   private ResultSet resultSet;
   
   
   public BaseConnection(String driver,String url){
	   this.driver=driver;
	   this.url=url;
	   createConnection();
   }
    public BaseConnection() {
    	createConnection();
    }
    
    private void createConnection() {
    	try {
    		connexion = DriverManager.getConnection(url);
    		statement = connexion.createStatement();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
   	
    public Statement getStatement() {
		return statement;
	}
	public void setStatement(Statement statement) {
		this.statement = statement;
	}
	public void insertion(String req) {
    	try {
               statement.executeQuery(req);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    public ResultSet selection(String req) {
    	try {
			resultSet=statement.executeQuery(req);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return resultSet;
    }
    public Connection getConnexion() {
	  return connexion;
   	}
   
   
}
