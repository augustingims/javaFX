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
public class EtudiantsEncadrer {
   public SimpleStringProperty nomE= new SimpleStringProperty();
   public SimpleStringProperty prenomE = new SimpleStringProperty(); 
   public SimpleStringProperty filiereE = new SimpleStringProperty();
   public SimpleStringProperty niveauE = new SimpleStringProperty();
   public SimpleStringProperty themeE = new SimpleStringProperty();
   public SimpleStringProperty typeE = new SimpleStringProperty(); 

    public String getNomE() {
        return nomE.get();
    }

    public String getPrenomE() {
        return prenomE.get();
    }

    public String getFiliereE() {
        return filiereE.get();
    }

    public String getNiveauE() {
        return niveauE.get();
    }

    public String getThemeE() {
        return themeE.get();
    }

    public String getTypeE() {
        return typeE.get();
    }
   
   
}
