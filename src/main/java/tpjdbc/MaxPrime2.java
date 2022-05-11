/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpjdbc;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import static tpjdbc.Joueur.connexion;
import static tpjdbc.Joueur.database;
import static tpjdbc.Joueur.password;
import static tpjdbc.Joueur.port;
import static tpjdbc.Joueur.server;
import static tpjdbc.Joueur.user;

/**
 *
 * @author user
 */
public class MaxPrime2 {

    public void getLoopMaxPRime(String tournoi, int annee) {
        try {
            String requete = "select Max(PRIME) from Gain where annee= ? and lieutournoi=?;";
            Class c = Class.forName("com.mysql.jdbc.Driver");
            Driver pilote = (Driver) c.newInstance();
            DriverManager.registerDriver(pilote);
            String url = "jdbc:mysql://" + server + ":" + port + "/" + database;
            connexion = DriverManager.getConnection(url, user, password);
            PreparedStatement getMPrime
                    = connexion.prepareStatement(requete);
            getMPrime.setInt(1, annee);
            getMPrime.setString(2, tournoi);            
            ResultSet resultat = getMPrime.executeQuery();
            while (resultat.next()) {
                String tuple = resultat.getString(1);
                System.out.println("La prime maximale du tounoi " + tournoi + " de l'année " + annee + " est de : " + tuple);
            }
            resultat.close();
            getMPrime.close();
            connexion.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Boolean next = true;
        Scanner sc = new Scanner(System.in);
        while (next) {
            System.out.print("Entrer le lieu du tournoi: ");
            String tournoi = sc.nextLine();
            if (tournoi.isBlank()) {
                System.out.println("out");
                next = false;
                break;
            }
            System.out.print("\nEntrer la date du tournoi: ");
            int annee = Integer.parseInt(sc.nextLine());
            MaxPrime2 maxPrime = new MaxPrime2();
            maxPrime.getLoopMaxPRime(tournoi, annee);

        }
    }

}
