/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpjdbc;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class Generique2 {

    private String requete;
    static String url = "jdbc:postgresql://localhost/test";
    static String user = "user";
    static String password = "Bismilah";
    static Connection connexion = null;

    ///connect to mysql
    static String server = "localhost";
    static String port = "3306";
    static String database = "base_tennis";
    static String mysql_user = "root";
    static String mysql_password = "Bismilah";
    //static String requete = "select nom,prenom from Joueur";
    static Connection mysql_connexion = null;

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;

    }

    public Generique2() {

        try {
            Class c_m = Class.forName("com.mysql.jdbc.Driver");
            Class c = Class.forName("org.postgresql.Driver");
            Driver pilote_m = (Driver) c_m.newInstance();
            DriverManager.registerDriver(pilote_m);
            Driver pilote = (Driver) c.newInstance();
            DriverManager.registerDriver(pilote);
            String url_m = "jdbc:mysql://" + server + ":" + port + "/" + database;
            mysql_connexion = DriverManager.getConnection(url_m, mysql_user, mysql_password);
            connexion = DriverManager.getConnection(url, user, password);
            String requeteb1 = "select nom,nationalite,nomSponsor from " + database + ".Joueur j inner join " + database + ".GAIN g on j.NuJOUEUR=g.NuJOUEUR;";
            Statement lecture_m = mysql_connexion.createStatement();
            ResultSet resultat_m = lecture_m.executeQuery(requeteb1);
            String requeteb2 = "select * from sponsor";
            Statement lecture = connexion.createStatement();
            long startTime = System.nanoTime();
            ResultSet resultat = lecture.executeQuery(requeteb2);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime)/1000000;
            System.out.println("la durée de la requete: "+duration+" millisecondes");

            resultat_m.close();
            lecture_m.close();
            mysql_connexion.close();
            resultat.close();
            lecture.close();
            connexion.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Generique2 maRequete = new Generique2();
        //Generique2 maRequete = new Generique2();
        System.out.println("gvgrjhfke" + maRequete.toString());
    }

}
