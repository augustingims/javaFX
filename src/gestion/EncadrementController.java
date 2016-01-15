/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author gims
 */
public class EncadrementController implements Initializable {

   @FXML private TableView<Encadrement> tableview;
    @FXML private TableColumn<Encadrement,String> matriculeENCL;
    @FXML private TableColumn<Encadrement,String> matriculeETCL;
    @FXML private TableColumn<Encadrement,String> datedebutCL;
    @FXML private TableColumn<Encadrement,String> datefinCL;
    @FXML private TableColumn<Encadrement,String> typeCL;
    @FXML private TableColumn<Encadrement,String> themeCL;
    @FXML private TableColumn<Encadrement,Integer> noteCL;
    
    @FXML private TextField matriculeENTF;
    @FXML private TextField matriculeETTF;
    @FXML private TextField datefinTF;
    @FXML private TextField datedebutTF;
    @FXML private ComboBox<String> typeCB;
    @FXML private TextField noteTF;
    @FXML private TextField themeTF;
    
     Connexion con=new Connexion();           
       private ObservableList<Encadrement> data;
       Statement stm;
       private int posicionPersonaEnTabla;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    typeCB.setItems(listtype);
    matriculeENCL.setCellValueFactory(new PropertyValueFactory("matriculeEN"));     
    matriculeETCL.setCellValueFactory(new PropertyValueFactory("matriculeET"));        
    typeCL.setCellValueFactory(new PropertyValueFactory("type"));    
    themeCL.setCellValueFactory(new PropertyValueFactory("theme"));        
    datedebutCL.setCellValueFactory(new PropertyValueFactory("datedebut"));
    datefinCL.setCellValueFactory(new PropertyValueFactory("datefin"));
    noteCL.setCellValueFactory(new PropertyValueFactory("note"));
    Connection connection;
        connection = con.ObtenirConnexion();
    buildData();
    }   
    
    ObservableList<String> listtype=FXCollections.observableArrayList("Stage","Projet","Memoire");
    
    public void buildData(){        
        data = FXCollections.observableArrayList();
     
     try{      
        String SQL = "Select * from encadrement";   
        stm=con.ObtenirConnexion().createStatement();
        ResultSet rs = stm.executeQuery(SQL);  
        while(rs.next()){
            Encadrement cm = new Encadrement();
            cm.matriculeEN.set(rs.getString("matricule_enseignant"));                       
            cm.matriculeET.set(rs.getString("matricule_etudiant"));
            cm.type.set(rs.getString("typeencad"));                       
            cm.theme.set(rs.getString("theme"));
            cm.datedebut.set(rs.getString("date_debut"));
            cm.datefin.set(rs.getString("date_fin"));
            cm.note.set(rs.getInt("note"));
            data.add(cm);  
        }
        tableview.setItems(data);
    }
    catch(Exception e){
          e.printStackTrace();
          System.out.println("Error on Building Data");            
    }
}
    private final ListChangeListener<Encadrement> selectorTablaPresonas =
            new ListChangeListener<Encadrement>(){
            @Override
            public void onChanged(ListChangeListener.Change<? extends Encadrement> c){
               ponerPersonaSeleccionada();
              }
            };
    public Encadrement getTablaPersonasSeleccionada(){
          if(tableview != null){
          List<Encadrement> tabla = tableview.getSelectionModel().getSelectedItems();
             if(tabla.size()== 1){
             final Encadrement competicionSeleccionada = tabla.get(0);
             return competicionSeleccionada;
             }
          }
          return null;
    }
    private void ponerPersonaSeleccionada(){
      final Encadrement persona = getTablaPersonasSeleccionada();
      posicionPersonaEnTabla =data.indexOf(persona);
         
       if(persona != null){
       matriculeENTF.setText(persona.getMatriculeEN());
       matriculeETTF.setText(persona.getMatriculeET());
       datedebutTF.setText(persona.getDatedebut());
       themeTF.setText(persona.getTheme());
       datefinTF.setText(persona.getDatefin());
       typeCB.setValue(persona.getType());
       }
    }
   @FXML
   public void tableviewMouseClicked(MouseEvent evt){
     final ObservableList<Encadrement> tablaPersonaSel = tableview.getSelectionModel().getSelectedItems();
       tablaPersonaSel.addListener(selectorTablaPresonas);
   }
     public void vider(){
       matriculeENTF.setText("");
       matriculeETTF.setText("");
       datedebutTF.setText("");
       themeTF.setText("");
       datefinTF.setText("");
       typeCB.setValue("");
     }
     public void modifierBT(ActionEvent evt){
     
        try{
            if(JOptionPane.showConfirmDialog(null,"Confirmer la modification","modification",JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION)
            {
            stm=con.ObtenirConnexion().createStatement();    
            stm.executeUpdate("UPDATE encadrement SET typeencad='"+typeCB.getValue()+"',theme='"+themeTF.getText()+"',date_debut='"+datedebutTF.getText()+"',"
                    + "date_fin='"+datefinTF.getText()+"' where matricule_enseignant='"+matriculeENTF.getText()+"' and matricule_etudiant='"+matriculeETTF.getText()+"'");
           buildData();
           vider();
            }
        
    }catch (Exception e) {
	JOptionPane.showMessageDialog(null,"erreur de modification"+e.getMessage());
        System.err.println(e);
			}
    
   }
     @FXML
   public void ajouterBT(ActionEvent evt){
    String mat=matriculeENTF.getText();
    String nom=matriculeETTF.getText();
    String pren=typeCB.getValue();
    String the=themeTF.getText();
    String fil=datedebutTF.getText();
    String ty=datefinTF.getText();
    boolean bool=true;
    String requete="insert into encadrement (matricule_enseignant,matricule_etudiant,typeencad,theme,date_debut,date_fin,status) values ('"+mat+"','"+nom+"','"+pren+"','"+the+"','"+fil+"','"+ty+"',"+bool+")";
        try{
    stm=con.ObtenirConnexion().createStatement();        
    stm.executeUpdate(requete);
    JOptionPane.showMessageDialog(null,"l'encadrement a été belle et bien Ajouter");
    buildData();
    vider();
    }catch(Exception ex){
    JOptionPane.showMessageDialog(null,ex.getMessage());
    }  
   
   }
   
    @FXML
   public void cloturerBT(ActionEvent evt){
    String mat=matriculeENTF.getText();
    String nom=matriculeETTF.getText();
    try{
    if(!mat.equals("") && !nom.equals("")){
     TextInputDialog dialog = new TextInputDialog("");  
     dialog.setTitle ("Cloture");
     dialog.setHeaderText("Etez-vous sur de Cloturer cet Encadrement");
     dialog.setContentText("veillez entrer la Note:");
     
     Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            Long note = Long.parseLong(result.get());
             boolean bool=false;
            stm=con.ObtenirConnexion().createStatement();
            stm.executeUpdate("UPDATE encadrement SET typeencad='"+typeCB.getValue()+"',theme='"+themeTF.getText()+"',date_debut='"+datedebutTF.getText()+"'"
                    + " ,date_fin='"+datefinTF.getText()+"' ,status="+bool+" ,note ="+note+" where matricule_enseignant='"+matriculeENTF.getText()+"' and matricule_etudiant='"+matriculeETTF.getText()+"'");
           buildData();
           vider();
           System.out.println("Your name: " + result.get());
        }
   }
    }catch(Exception e){
    JOptionPane.showMessageDialog(null,"erreur de modification"+e.getMessage());
    System.err.println(e);
    }  
  }
}
