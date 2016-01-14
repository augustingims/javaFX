/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author M-GIMS
 */
public class Connexion {
    
     /**
	* URL de connection
	*/
	private static String url =
	"jdbc:mysql://localhost:3306/gestion_encadrement";
	/**
	* Nom du user
	*/
	private static String user = "root";
	/**
	* Mot de passe du user
	*/
	private static String passwd = "";
	/**
	* Objet Connection
	*/
	 Connection con;
	/**
	* Méthode qui va retourner notre instance
	* et la créer si elle n'existe pas...
	* @return
	*/
	public  Connexion(){
            
		try{
                
                 Class.forName("com.mysql.jdbc.Driver");
                 System.out.println("pilote chargé");
               }catch(ClassNotFoundException ex){
                   System.out.println(ex);
               }
		try {
			con= DriverManager.getConnection(url, user, passwd);
                        System.out.println("Connexion à la base de données");
			} catch (SQLException ex) {
			System.out.println(ex);
			}
		
	}
     public Connection ObtenirConnexion(){
        
            return con;
        }

    
}
