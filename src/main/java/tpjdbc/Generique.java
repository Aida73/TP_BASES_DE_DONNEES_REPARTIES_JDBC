/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpjdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import static tpjdbc.Joueur.connexion;
import static tpjdbc.Joueur.database;
import static tpjdbc.Joueur.password;
import static tpjdbc.Joueur.port;
import static tpjdbc.Joueur.requete;
import static tpjdbc.Joueur.server;
import static tpjdbc.Joueur.user;

/**
 *
 * @author user
 */
public class Generique {
    private String requete;
    public Generique(String requete) {
        this.requete = requete;
        try {
            Class c = Class.forName("com.mysql.jdbc.Driver");
            Driver pilote = (Driver)c.newInstance();
            DriverManager.registerDriver(pilote);
            String url = "jdbc:mysql://" +server + ":" +port +"/"+database;
            connexion = DriverManager.getConnection(url, user,password);
            Statement lecture = connexion.createStatement();
            ResultSet resultat = lecture.executeQuery(requete);
            ResultSetMetaData rsmd = resultat.getMetaData();
          
            System.out.println(rsmd.getColumnName(1)+" "+rsmd.getColumnName(2)+" "+rsmd.getColumnName(3)+" "+rsmd.getColumnName(4)+" "+rsmd.getColumnName(5));
            while (resultat.next()) {
                String tuple = resultat.getString(1) + " "+resultat.getString(2) + " "+resultat.getString(3) + " "+resultat.getString(4) + " "+resultat.getString(5);
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
    
    public static void main(String[] args) {
        Generique maRequete = new Generique("select * from Joueur where annaiss = 1972");
        System.out.println("gvgrjhfke"+maRequete);
    }
    
    
    
}
