package tpjdbc;

import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import static tpjdbc.Config.connexion;
import static tpjdbc.Config.database;
import static tpjdbc.Config.password;
import static tpjdbc.Config.port;
import static tpjdbc.Config.server;
import static tpjdbc.Config.user;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
public class Schema {
    
    private String requete;

    public Schema(String requete) {
        this.requete = requete;
        try {
            Class c = Class.forName("com.mysql.jdbc.Driver");
            Driver pilote = (Driver)c.newInstance();
            DriverManager.registerDriver(pilote);
            String url = "jdbc:mysql://" +server + ":" +port +"/"+database;
            connexion = DriverManager.getConnection(url, user,password);
            Statement lecture = connexion.createStatement();
            ResultSet resultat = lecture.executeQuery(requete);
            DatabaseMetaData dmd = connexion.getMetaData();
            ResultSet rsColumns = dmd.getColumns(null,null,"Joueur",null);
            System.out.print("NOM"+" " +"TYPE\n");
            while (rsColumns.next()) {
              System.out.print(rsColumns.getString("COLUMN_NAME")+" ");
              System.out.print(rsColumns.getString("TYPE_NAME")+"\n");
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
        Schema shema = new Schema("select * from Joueur where annaiss = 1972");
        System.out.println(shema);
    }
    
    
    
    
    
}
