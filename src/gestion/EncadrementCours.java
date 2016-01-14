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
public class EncadrementCours {
    
   public SimpleStringProperty nomEN= new SimpleStringProperty();
   public SimpleStringProperty prenomEN = new SimpleStringProperty(); 
   public SimpleStringProperty filiereEN = new SimpleStringProperty();
   public SimpleStringProperty niveauEN = new SimpleStringProperty();
   public SimpleStringProperty typeEN = new SimpleStringProperty();
   public SimpleStringProperty themeEN = new SimpleStringProperty(); 
   public SimpleStringProperty datedebutEN = new SimpleStringProperty();
   public SimpleStringProperty datefinEN = new SimpleStringProperty();
   public SimpleStringProperty anneeEN = new SimpleStringProperty();

    public String getNomEN() {
        return nomEN.get();
    }

    public String getPrenomEN() {
        return prenomEN.get();
    }

    public String getFiliereEN() {
        return filiereEN.get();
    }

    public String getNiveauEN() {
        return niveauEN.get();
    }

    public String getTypeEN() {
        return typeEN.get();
    }

    public String getThemeEN() {
        return themeEN.get();
    }

    public String getDatedebutEN() {
        return datedebutEN.get();
    }

    public String getDatefinEN() {
        return datefinEN.get();
    }

    public String getAnneeEN() {
        return anneeEN.get();
    }
    
    
}
