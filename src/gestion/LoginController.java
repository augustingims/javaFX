/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gims
 */
public class LoginController implements Initializable {

  @FXML
  private TextField username;
  @FXML
  private TextField password;
  @FXML
  private Label message;
  
   Connexion con=new Connexion();           
      
   Statement stm;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void refresh(){
        username.setText("");
        password.setText(""); 
    }
    
    @FXML
    public void LoginAction(ActionEvent event) throws IOException{
        try{        
        String SQL = "Select * from enseignant where login = '" + username.getText() + "' and password = '" + password.getText() + "'";   
        stm=con.ObtenirConnexion().createStatement();
        ResultSet rs = stm.executeQuery(SQL);
    
        if(rs.next()){
         refresh();
         Parent parent = FXMLLoader.load(getClass().getResource("Home.fxml"));
         Scene scene = new Scene(parent);
         Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
         stage.hide();
         stage.setScene(scene);
         stage.show();
         message.setText("Successful connect");
         }  
        else{
            message.setText("Informations incorrect");
        }
       
         }catch(Exception e){
          e.printStackTrace();     
         }
    }
     public void Quitter(ActionEvent event){
        Platform.exit();
    }
}
