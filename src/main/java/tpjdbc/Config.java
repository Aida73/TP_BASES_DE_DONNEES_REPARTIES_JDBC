package tpjdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import static tpjdbc.Joueur.connexion;
import static tpjdbc.Joueur.database;
import static tpjdbc.Joueur.password;
import static tpjdbc.Joueur.port;
import static tpjdbc.Joueur.requete;
import static tpjdbc.Joueur.server;
import static tpjdbc.Joueur.user;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
public class Config {
    
    static String server = "localhost";
	static String port = "3306";
	static String database = "base_tennis";
	static String user = "root";
	static String password = "Bismilah";
	static String requete = "";
	static Connection connexion = null;

    public Config(String requete) {
        try {
            Class c = Class.forName("com.mysql.jdbc.Driver");
            Driver pilote = (Driver)c.newInstance();
            DriverManager.registerDriver(pilote);
            String url = "jdbc:mysql://" +server + ":" +port +"/"+database;
            connexion = DriverManager.getConnection(url, user,password);
            Statement lecture = connexion.createStatement();
            ResultSet resultat = lecture.executeQuery(requete);
            while (resultat.next()) {
                String tuple = resultat.getString(1) + " "+resultat.getString(2);
                System.out.println(tuple);
            }
            resultat.close();
            lecture.close();
            connexion.close();

            }
        catch(Exception e) {
                e.printStackTrace();
        }
    }
        
        
    
}
