/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author gims
 */
public class HomeController implements Initializable {
    // Column Encadrement en Cours
    @FXML private TableView<EncadrementCours> Encours;
    @FXML private TableColumn<EncadrementCours,String> nomENCL;
    @FXML private TableColumn<EncadrementCours,String> prenomENCL;
    @FXML private TableColumn<EncadrementCours,String> filiereENCL;
    @FXML private TableColumn<EncadrementCours,String> niveauENCL;
    @FXML private TableColumn<EncadrementCours,String> typeENCL;
    @FXML private TableColumn<EncadrementCours,String> themeENCL;
    @FXML private TableColumn<EncadrementCours,String> datedebutENCL;
    @FXML private TableColumn<EncadrementCours,String> datefinENCL;
    @FXML private TableColumn<EncadrementCours,String> anneeENCL;
    
    // Column Encadrement Cloturer
    @FXML private TableView<EncadrementCloturer> Cloturer;
    @FXML private TableColumn<EncadrementCloturer,String> nomCL;
    @FXML private TableColumn<EncadrementCloturer,String> prenomCL;
    @FXML private TableColumn<EncadrementCloturer,String> filiereCL;
    @FXML private TableColumn<EncadrementCloturer,String> niveauCL;
    @FXML private TableColumn<EncadrementCloturer,String> typeCL;
    @FXML private TableColumn<EncadrementCloturer,String> themeCL;
    @FXML private TableColumn<EncadrementCloturer,String> datedebutCL;
    @FXML private TableColumn<EncadrementCloturer,String> datefinCL;
    @FXML private TableColumn<EncadrementCloturer,String> anneeCL;
     
    //Etudiants Encadrer 
    @FXML private TableView<EtudiantsEncadrer> Encadrer;
    @FXML private TableColumn<EtudiantsEncadrer,String> nomE;
    @FXML private TableColumn<EtudiantsEncadrer,String> prenomE;
    @FXML private TableColumn<EtudiantsEncadrer,String> filiereE;
    @FXML private TableColumn<EtudiantsEncadrer,String> niveauE;
    @FXML private TableColumn<EtudiantsEncadrer,String> typeE;
    @FXML private TableColumn<EtudiantsEncadrer,String> themeE;
    
    //Année Academique
    @FXML private TableView<Annee> Annee;
    @FXML private TableColumn<Annee,Integer> id;
    @FXML private TableColumn<Annee,String> nom;
    @FXML private TextField nomTF;
    
    //Bilan
    @FXML private ComboBox<String> anneeCB;     

     Connexion con=new Connexion();           
      private ObservableList<EncadrementCours> data;
      private ObservableList<EncadrementCloturer> data1;
      private ObservableList<EtudiantsEncadrer> data2;
      private ObservableList<Annee> data3;
      private ObservableList<String> listann;
      Statement stm;
       
       public void encadrementCoursData(){        
        data = FXCollections.observableArrayList();
     
     try{      
        String SQL ="select etu.nom, etu.prenom, etu.filiere, etu.niveau, en.typeencad, en.theme, en.date_debut, en.date_fin, an.nomA\n" +
                    "from etudiant etu, encadrement en, enseignant ens, annee an\n" +
                    "where etu.matricule_etudiant=en.matricule_etudiant \n" +
                    "and ens.matricule_enseignant=en.matricule_enseignant\n" +
                    "and en.matricule_enseignant=an.matricule_enseignant\n" +
                    "and en.status = 1";   
        stm=con.ObtenirConnexion().createStatement();
        ResultSet rs = stm.executeQuery(SQL);  
        while(rs.next()){
            EncadrementCours cm = new EncadrementCours();
            cm.nomEN.set(rs.getString("nom"));                       
            cm.prenomEN.set(rs.getString("prenom"));
            cm.filiereEN.set(rs.getString("filiere"));                       
            cm.niveauEN.set(rs.getString("niveau"));
            cm.typeEN.set(rs.getString("typeencad"));
            cm.themeEN.set(rs.getString("theme"));
            cm.datedebutEN.set(rs.getString("date_debut"));
            cm.datefinEN.set(rs.getString("date_fin"));
            cm.anneeEN.set(rs.getString("nomA"));
            data.add(cm);  
        }
        Encours.setItems(data);
    }
    catch(Exception e){
          e.printStackTrace();
          System.out.println("Error on Building Data");            
    }
       }
       
        public void chargementAnneeCB(){
           listann = FXCollections.observableArrayList();
       try{
             String SQL = "Select an.nomA from annee an";
             stm=con.ObtenirConnexion().createStatement();
             ResultSet rs = stm.executeQuery(SQL);  
             while(rs.next()){
                 Annee ann = new Annee();
                 ann.annee.set(rs.getString("nomA"));
                 listann.add(ann.getAnnee());
             }
             anneeCB.setItems(listann);
         }
         catch(Exception e){
             e.printStackTrace();
          System.out.println("Error on Building Data");  
         }
       }
        
        public void encadrementCloturerData(){        
        data1 = FXCollections.observableArrayList();
     
     try{      
        String SQL ="select etu.nom, etu.prenom, etu.filiere, etu.niveau, en.typeencad, en.theme, en.date_debut, en.date_fin, an.nomA\n" +
                    "from etudiant etu, encadrement en, enseignant ens, annee an\n" +
                    "where etu.matricule_etudiant=en.matricule_etudiant \n" +
                    "and ens.matricule_enseignant=en.matricule_enseignant\n" +
                    "and en.matricule_enseignant=an.matricule_enseignant\n" +
                    "and en.status = 0;";   
        stm=con.ObtenirConnexion().createStatement();
        ResultSet rs = stm.executeQuery(SQL);  
        while(rs.next()){
            EncadrementCloturer cm = new EncadrementCloturer();
            cm.nomCL.set(rs.getString("nom"));                       
            cm.prenomCL.set(rs.getString("prenom"));
            cm.filiereCL.set(rs.getString("filiere"));                       
            cm.niveauCL.set(rs.getString("niveau"));
            cm.typeCL.set(rs.getString("typeencad"));
            cm.themeCL.set(rs.getString("theme"));
            cm.datedebutCL.set(rs.getString("date_debut"));
            cm.datefinCL.set(rs.getString("date_fin"));
            cm.anneeCL.set(rs.getString("nomA"));
            data1.add(cm);  
        }
        Cloturer.setItems(data1);
    }
    catch(Exception e){
          e.printStackTrace();
          System.out.println("Error on Building Data");            
    }
       }
        
        public void etudiantsEncadrerData(){        
        data2 = FXCollections.observableArrayList();
     
     try{      
        String SQL ="select etu.nom, etu.prenom, etu.filiere, etu.niveau, en.typeencad, en.theme\n" +
                    "from etudiant etu, encadrement en\n" +
                    "where etu.matricule_etudiant=en.matricule_etudiant;";   
        stm=con.ObtenirConnexion().createStatement();
        ResultSet rs = stm.executeQuery(SQL);  
        while(rs.next()){
            EtudiantsEncadrer cm = new EtudiantsEncadrer();
            cm.nomE.set(rs.getString("nom"));                       
            cm.prenomE.set(rs.getString("prenom"));
            cm.filiereE.set(rs.getString("filiere"));                       
            cm.niveauE.set(rs.getString("niveau"));
            cm.typeE.set(rs.getString("typeencad"));
            cm.themeE.set(rs.getString("theme"));
            data2.add(cm);  
        }
        Encadrer.setItems(data2);
    }
    catch(Exception e){
          e.printStackTrace();
          System.out.println("Error on Building Data");            
    }
       }
        
        public void anneeData(){        
        data3 = FXCollections.observableArrayList();
     
     try{      
        String SQL ="select * from annee";   
        stm=con.ObtenirConnexion().createStatement();
        ResultSet rs = stm.executeQuery(SQL);  
        while(rs.next()){
            Annee cm = new Annee();
            cm.numero.set(rs.getInt("id_annee"));                       
            cm.annee.set(rs.getString("nomA"));
            data3.add(cm);  
        }
        Annee.setItems(data3);
    }
    catch(Exception e){
          e.printStackTrace();
          System.out.println("Error on Building Data");            
    }
       }
    @FXML
    private void EtudiantAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("Etudiants.fxml"));
         Scene scene = new Scene(parent);
         Stage stage = new Stage();
         stage.setScene(scene);
         stage.show();
    }
    @FXML
    private void AboutAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("About.fxml"));
         Scene scene = new Scene(parent);
         Stage stage = new Stage();
         stage.setScene(scene);
         stage.show();
    }
  
    @FXML
    private void BilanAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("Bilan.fxml"));
         Scene scene = new Scene(parent);
         Stage stage = new Stage();
         stage.setScene(scene);
         stage.show();
    }
     @FXML
    private void EncadrementAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("Encadrement.fxml"));
         Scene scene = new Scene(parent);
         Stage stage = new Stage();
         stage.setScene(scene);
         stage.show();
    }
    
     @FXML
    private void CreerAnnee(ActionEvent event){
    String annee=nomTF.getText();
    String requete="insert into annee (nomA) values ('"+annee+"')";
        try{
    stm=con.ObtenirConnexion().createStatement();        
    stm.executeUpdate(requete);
    JOptionPane.showMessageDialog(null,"l'année a été belle et bien Creer");
    anneeData();
    nomTF.setText("");
    }catch(Exception ex){
    JOptionPane.showMessageDialog(null,ex.getMessage());
    }  
   
}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Encadrement en cours
    nomENCL.setCellValueFactory(new PropertyValueFactory("nomEN"));     
    prenomENCL.setCellValueFactory(new PropertyValueFactory("prenomEN"));        
    filiereENCL.setCellValueFactory(new PropertyValueFactory("filiereEN"));    
    niveauENCL.setCellValueFactory(new PropertyValueFactory("niveauEN"));        
    typeENCL.setCellValueFactory(new PropertyValueFactory("typeEN"));
    themeENCL.setCellValueFactory(new PropertyValueFactory("themeEN"));
    datedebutENCL.setCellValueFactory(new PropertyValueFactory("datedebutEN"));
    datefinENCL.setCellValueFactory(new PropertyValueFactory("datefinEN"));
    anneeENCL.setCellValueFactory(new PropertyValueFactory("anneeEN"));
    
    //Encadrement Cloturer
    nomCL.setCellValueFactory(new PropertyValueFactory("nomCL"));     
    prenomCL.setCellValueFactory(new PropertyValueFactory("prenomCL"));        
    filiereCL.setCellValueFactory(new PropertyValueFactory("filiereCL"));    
    niveauCL.setCellValueFactory(new PropertyValueFactory("niveauCL"));        
    typeCL.setCellValueFactory(new PropertyValueFactory("typeCL"));
    themeCL.setCellValueFactory(new PropertyValueFactory("themeCL"));
    datedebutCL.setCellValueFactory(new PropertyValueFactory("datedebutCL"));
    datefinCL.setCellValueFactory(new PropertyValueFactory("datefinCL"));
    anneeCL.setCellValueFactory(new PropertyValueFactory("anneeCL"));
    
    //Etudinats Encadrer
    nomE.setCellValueFactory(new PropertyValueFactory("nomE"));     
    prenomE.setCellValueFactory(new PropertyValueFactory("prenomE"));        
    filiereE.setCellValueFactory(new PropertyValueFactory("filiereE"));    
    niveauE.setCellValueFactory(new PropertyValueFactory("niveauE"));        
    typeE.setCellValueFactory(new PropertyValueFactory("typeE"));
    themeE.setCellValueFactory(new PropertyValueFactory("themeE"));
    
    //Année Academique
    id.setCellValueFactory(new PropertyValueFactory("numero"));     
    nom.setCellValueFactory(new PropertyValueFactory("annee"));  
    
    Connection connection;
        connection = con.ObtenirConnexion();
    encadrementCoursData();
    encadrementCloturerData();
    etudiantsEncadrerData();
    anneeData();
    chargementAnneeCB();
    }    
    
}
