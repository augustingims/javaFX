/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author gims
 */
public class BilanController implements Initializable {

    @FXML private TableView<Bilan> tableview;
    @FXML private TableColumn<Bilan,String> anneeCL;
    @FXML private TableColumn<Bilan,String> sceanceCL;
    @FXML private TableColumn<Bilan,String> nomCL;
    @FXML private TableColumn<Bilan,String> themeCL;
    @FXML private TableColumn<Bilan,String> bilanCL;
    @FXML private TableColumn<Bilan,String> tafCL;
    @FXML private TableColumn<Bilan,String> dateCL;
    @FXML private TableColumn<Bilan,String> heureCL;
    
    Connexion con=new Connexion();           
       private ObservableList<Bilan> data;
       Statement stm;
       
       
        public void bilanData(){        
        data = FXCollections.observableArrayList();
     
     try{      
        String SQL = "select an.nomA, sc.nomsc, etu.nom,en.theme,bi.observation,bi.taf,sc.date_seance,sc.heure_seance\n" +
                        "from etudiant etu, annee an, encadrement en, seance sc, enseignant ens,bilan bi\n" +
                        "where etu.matricule_etudiant=en.matricule_etudiant\n" +
                        "and ens.matricule_enseignant=en.matricule_enseignant\n" +
                        "and ens.matricule_enseignant=an.matricule_enseignant\n" +
                        "and bi.id_bilan=sc.id_bilan";   
        stm=con.ObtenirConnexion().createStatement();
        ResultSet rs = stm.executeQuery(SQL);  
        while(rs.next()){
            Bilan cm = new Bilan();
            cm.annee.set(rs.getString("nomA"));                       
            cm.sceance.set(rs.getString("nomsc"));
            cm.etudiant.set(rs.getString("nom"));
            cm.theme.set(rs.getString("theme"));
            cm.bilan.set(rs.getString("observation"));
            cm.taf.set(rs.getString("taf"));
            cm.date.set(rs.getString("date_seance"));
            cm.heure.set(rs.getString("heure_seance"));
            data.add(cm);  
        }
        tableview.setItems(data);
    }
    catch(Exception e){
          e.printStackTrace();
          System.out.println("Error on Building Data");            
    }
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    anneeCL.setCellValueFactory(new PropertyValueFactory("annee"));     
    sceanceCL.setCellValueFactory(new PropertyValueFactory("sceance"));        
    themeCL.setCellValueFactory(new PropertyValueFactory("theme"));           
    bilanCL.setCellValueFactory(new PropertyValueFactory("bilan"));
     tafCL.setCellValueFactory(new PropertyValueFactory("taf"));
      dateCL.setCellValueFactory(new PropertyValueFactory("date"));
     heureCL.setCellValueFactory(new PropertyValueFactory("heure"));
    Connection connection;
        connection = con.ObtenirConnexion();
    bilanData();
    }    
    
}
