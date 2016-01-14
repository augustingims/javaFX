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
public class Encadrement {
    
   public SimpleStringProperty matriculeEN= new SimpleStringProperty();
   public SimpleStringProperty matriculeET= new SimpleStringProperty();
   public SimpleStringProperty status = new SimpleStringProperty();
   public SimpleStringProperty theme = new SimpleStringProperty();
   public SimpleStringProperty type = new SimpleStringProperty(); 
   public SimpleStringProperty datefin = new SimpleStringProperty();
   public SimpleStringProperty datedebut = new SimpleStringProperty(); 
   public SimpleIntegerProperty note = new SimpleIntegerProperty();

    public String getMatriculeEN() {
        return matriculeEN.get();
    }

    public String getMatriculeET() {
        return matriculeET.get();
    }

    public String getStatus() {
        return status.get();
    }

    public String getTheme() {
        return theme.get();
    }

    public String getType() {
        return type.get();
    }

    public String getDatefin() {
        return datefin.get();
    }

    public String getDatedebut() {
        return datedebut.get();
    }

    public Integer getNote() {
        return note.get();
    }
   
   
}
