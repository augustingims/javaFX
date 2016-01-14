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
 * @author gims
 */
public class Annee {
    
   public SimpleStringProperty annee = new SimpleStringProperty(); 
   public SimpleIntegerProperty numero = new SimpleIntegerProperty();

    public String getAnnee() {
        return annee.get();
    }

    public Integer getNumero() {
        return numero.get();
    }
    
}
