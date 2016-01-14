/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author M-GIMS
 */
public class Etudiants {
    
   public SimpleStringProperty  matricule= new SimpleStringProperty();
   public SimpleStringProperty noms = new SimpleStringProperty(); 
   public SimpleStringProperty prenoms = new SimpleStringProperty();
   public SimpleStringProperty filiere = new SimpleStringProperty();
   public SimpleIntegerProperty niveau = new SimpleIntegerProperty();

    public String getMatricule() {
        return matricule.get();
    }

    public String getNoms() {
        return noms.get();
    }

    public String getPrenoms() {
        return prenoms.get();
    }

    public Integer getNiveau() {
        return niveau.get();
    }

    public String getFiliere() {
        return filiere.get();
    }
   
}
