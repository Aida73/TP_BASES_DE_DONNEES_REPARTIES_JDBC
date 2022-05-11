/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpjdbc;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
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
public class MaxPrime {

    public void getMaxPRime(String tournoi, int annee) {
        try {
            String tournoi_s = '"' + tournoi + '"';
            String requete = "select Max(PRIME) from Gain where annee=" + annee + " and lieutournoi=" + tournoi_s + ";";
            Class c = Class.forName("com.mysql.jdbc.Driver");
            Driver pilote = (Driver) c.newInstance();
            DriverManager.registerDriver(pilote);
            String url = "jdbc:mysql://" + server + ":" + port + "/" + database;
            connexion = DriverManager.getConnection(url, user, password);
            Statement lecture = connexion.createStatement();
            ResultSet resultat = lecture.executeQuery(requete);
            while (resultat.next()) {
                String tuple = resultat.getString(1);
                System.out.println("La prime maximale du tounoi " + tournoi + " de l'année " + annee + " est de : " + tuple);
            }
            resultat.close();
            lecture.close();
            connexion.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Entrer le lieu du tournoi: ");
        String tournoi = sc.nextLine();
        System.out.print("\nEntrer la date du tournoi: ");
        int annee = Integer.parseInt(sc.nextLine());

        MaxPrime maxPrime = new MaxPrime();
        maxPrime.getMaxPRime(tournoi, annee);
    }

}
