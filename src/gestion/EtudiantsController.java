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
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author gims
 */
public class EtudiantsController implements Initializable {

    @FXML private TableView<Etudiants> tableview;
    @FXML private TableColumn<Etudiants,String> matriculeCL;
    @FXML private TableColumn<Etudiants,String> nomsCL;
    @FXML private TableColumn<Etudiants,String> prenomsCL;
    @FXML private TableColumn<Etudiants,String> filiereCL;
    @FXML private TableColumn<Etudiants,Integer> niveauCL;
    
    @FXML private TextField matriculeTF;
    @FXML private TextField nomsTF;
    @FXML private TextField prenomsTF;
    @FXML private ComboBox<String> filiereCB;
    @FXML private ComboBox<String> niveauCB;
    
     Connexion con=new Connexion();           
       private ObservableList<Etudiants> data;
       Statement stm;
       private int posicionPersonaEnTabla;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    niveauCB.setItems(listniv);
    filiereCB.setItems(listfi);
    matriculeCL.setCellValueFactory(new PropertyValueFactory("matricule"));     
    nomsCL.setCellValueFactory(new PropertyValueFactory("noms"));        
    prenomsCL.setCellValueFactory(new PropertyValueFactory("prenoms"));           
    filiereCL.setCellValueFactory(new PropertyValueFactory("filiere"));
     niveauCL.setCellValueFactory(new PropertyValueFactory("niveau"));
    Connection connection;
        connection = con.ObtenirConnexion();
    buildData();
    }   
    
    ObservableList<String> listniv=FXCollections.observableArrayList("1","2","3");
    ObservableList<String> listfi=FXCollections.observableArrayList("GI","GRT","GEII","GBM","GAPMO","OGA","GLT");
    ObservableList<String> listtype=FXCollections.observableArrayList("Stage","Projet","Memoire");
    
    public void buildData(){        
        data = FXCollections.observableArrayList();
     
     try{      
        String SQL = "Select * from etudiant";   
        stm=con.ObtenirConnexion().createStatement();
        ResultSet rs = stm.executeQuery(SQL);  
        while(rs.next()){
            Etudiants cm = new Etudiants();
            cm.matricule.set(rs.getString("matricule_etudiant"));                       
            cm.noms.set(rs.getString("nom"));
            cm.prenoms.set(rs.getString("prenom"));
            cm.filiere.set(rs.getString("filiere"));
            cm.niveau.set(rs.getInt("niveau"));
            data.add(cm);  
        }
        tableview.setItems(data);
    }
    catch(Exception e){
          e.printStackTrace();
          System.out.println("Error on Building Data");            
    }
}
     private final ListChangeListener<Etudiants> selectorTablaPresonas =
            new ListChangeListener<Etudiants>(){
            @Override
            public void onChanged(ListChangeListener.Change<? extends Etudiants> c){
               ponerPersonaSeleccionada();
              }
            };
    public Etudiants getTablaPersonasSeleccionada(){
          if(tableview != null){
          List<Etudiants> tabla = tableview.getSelectionModel().getSelectedItems();
             if(tabla.size()== 1){
             final Etudiants competicionSeleccionada = tabla.get(0);
             return competicionSeleccionada;
             }
          }
          return null;
    }
    private void ponerPersonaSeleccionada(){
      final Etudiants persona = getTablaPersonasSeleccionada();
      posicionPersonaEnTabla =data.indexOf(persona);
         
       if(persona != null){
       matriculeTF.setText(persona.getMatricule());
       nomsTF.setText(persona.getNoms());
       prenomsTF.setText(persona.getPrenoms());
       filiereCB.setValue(persona.getFiliere());
       niveauCB.setValue(persona.getNiveau().toString());
       }
    }
   @FXML
   public void tableviewMouseClicked(MouseEvent evt){
     final ObservableList<Etudiants> tablaPersonaSel = tableview.getSelectionModel().getSelectedItems();
       tablaPersonaSel.addListener(selectorTablaPresonas);
   }
     public void vider(){
            matriculeTF.setText("");
            nomsTF.setText("");
            prenomsTF.setText("");
            filiereCB.setValue("");
            niveauCB.setValue("");
     }
     
   @FXML
   public void modifierBT(ActionEvent evt){
     
        try{
            if(JOptionPane.showConfirmDialog(null,"Confirmer la modification","modification",JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION)
            {
            stm=con.ObtenirConnexion().createStatement();    
            stm.executeUpdate("UPDATE etudiant SET nom='"+nomsTF.getText()+"',prenom='"+prenomsTF.getText()+"','"
                    + "filiere='"+filiereCB.getValue()+"','"
                    + "niveau='"+niveauCB.getValue()+"' where matricule_etudiant='"+matriculeTF.getText()+"'");
           buildData();
           vider();
            }
        
    }catch (Exception e) {
	JOptionPane.showMessageDialog(null,"erreur de modification"+e.getMessage());
        System.err.println(e);
			}
    
   }
         
   @FXML
   public void supprimerBT(ActionEvent evt){
    
       try{
            if(JOptionPane.showConfirmDialog(null,"Attention vous allez supprimer un etudiant, etez vous sur ?","supprimer etudiant",JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION)
            
             if(matriculeTF.getText().length()!=0){
     stm=con.ObtenirConnexion().createStatement();   
     stm.executeUpdate("DELETE from etudiant where matricule_etudiant= '"+matriculeTF.getText()+"'");
     buildData();
     vider();
             }else{
                JOptionPane.showMessageDialog(null,"veillez remplir le champ matricule!");    
             }
        
    }catch (Exception e) {
	JOptionPane.showMessageDialog(null,"erreur de suppression\n"+e.getMessage());
			}
   }
         
   
 @FXML
   public void ajouterBT(ActionEvent evt){
    String mat=matriculeTF.getText();
    String nom=nomsTF.getText();
    String pren=prenomsTF.getText();
    String fil=filiereCB.getValue();
    String niv=niveauCB.getValue();
    String requete="insert into etudiant (matricule_etudiant,nom,prenom,filiere,niveau) values ('"+mat+"','"+nom+"','"+pren+"','"+fil+"','"+niv+"')";
        try{
    stm=con.ObtenirConnexion().createStatement();        
    stm.executeUpdate(requete);
    JOptionPane.showMessageDialog(null,"l'etudiant a été belle et bien Ajouter");
    buildData();
    vider();
    }catch(Exception ex){
    JOptionPane.showMessageDialog(null,ex.getMessage());
    }  
   
   }
}
