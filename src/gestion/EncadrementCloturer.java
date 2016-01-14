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
public class EncadrementCloturer {
    
   public SimpleStringProperty nomCL= new SimpleStringProperty();
   public SimpleStringProperty prenomCL = new SimpleStringProperty(); 
   public SimpleStringProperty filiereCL = new SimpleStringProperty();
   public SimpleStringProperty niveauCL = new SimpleStringProperty();
   public SimpleStringProperty typeCL = new SimpleStringProperty();
   public SimpleStringProperty themeCL = new SimpleStringProperty(); 
   public SimpleStringProperty datedebutCL = new SimpleStringProperty();
   public SimpleStringProperty datefinCL = new SimpleStringProperty();
   public SimpleStringProperty anneeCL = new SimpleStringProperty();

    public String getNomCL() {
        return nomCL.get();
    }

    public String getPrenomCL() {
        return prenomCL.get();
    }

    public String getFiliereCL() {
        return filiereCL.get();
    }

    public String getNiveauCL() {
        return niveauCL.get();
    }

    public String getTypeCL() {
        return typeCL.get();
    }

    public String getThemeCL() {
        return themeCL.get();
    }

    public String getDatedebutCL() {
        return datedebutCL.get();
    }

    public String getDatefinCL() {
        return datefinCL.get();
    }

    public String getAnneeCL() {
        return anneeCL.get();
    }
    
   
}
