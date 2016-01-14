/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author gims
 */
public class Bilan {
   
   private int id;
   private String intitule;
    
   public SimpleStringProperty annee= new SimpleStringProperty();
   public SimpleStringProperty sceance = new SimpleStringProperty(); 
   public SimpleStringProperty etudiant = new SimpleStringProperty(); 
   public SimpleStringProperty theme = new SimpleStringProperty();
   public SimpleStringProperty bilan = new SimpleStringProperty();
   public SimpleStringProperty taf = new SimpleStringProperty();
   public SimpleStringProperty date = new SimpleStringProperty();
   public SimpleStringProperty heure = new SimpleStringProperty();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
   

    public String getAnnee() {
        return annee.get();
    }

    public String getSceance() {
        return sceance.get();
    }

    public String getTheme() {
        return theme.get();
    }

    public String getBilan() {
        return bilan.get();
    }

    public String getTaf() {
        return taf.get();
    }

    public String getDate() {
        return date.get();
    }

    public String getHeure() {
        return heure.get();
    }

    public String getEtudiant() {
        return etudiant.get();
    }
   
   
}
