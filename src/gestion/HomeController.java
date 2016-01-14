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
import javafx.collections.ListChangeListener;
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
import javafx.scene.input.MouseEvent;
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
    
    // Créer un RDV
    @FXML private TableView<CreerRDV> creerRDV;
    @FXML private TableColumn<CreerRDV,String> matEdtRDV;
    @FXML private TableColumn<CreerRDV,String> matEnseigRDV;
    @FXML private TableColumn<CreerRDV,String> nomSeanceRDV;
    @FXML private TableColumn<CreerRDV,String> billanRDV;
    @FXML private TableColumn<CreerRDV,String> heureRDV;
    @FXML private TableColumn<CreerRDV,String> dateRDV;
    
    @FXML private ComboBox<String> matEdtRDVCB;
    @FXML private ComboBox<String> matEnseigRDVCB;
    @FXML private TextField nomSeanceRDVTF;
    @FXML private ComboBox<String> billanRDVCB;
    @FXML private TextField heureRDVTF;
    @FXML private TextField dateRDVTF;
     
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

     Connexion con=new Connexion();           
      private ObservableList<EncadrementCours> data;
      private ObservableList<EncadrementCloturer> data1;
      private ObservableList<CreerRDV> dataRDV;
      private ObservableList<String> bilanRDV;
      private ObservableList<String> matEtdRDV;
      private ObservableList<String> matEnsRDV;
      private ObservableList<EtudiantsEncadrer> data2;
      private ObservableList<Annee> data3;
      Statement stm;
       
       public void encadrementCoursData(){        
        data = FXCollections.observableArrayList();
     
     try{      
        String SQL ="select etu.nom, etu.prenom, etu.filiere, etu.niveau, en.typeencad, en.theme, en.date_debut, en.date_fin\n" +
                    "from etudiant etu, encadrement en, enseignant ens\n" +
                    "where etu.matricule_etudiant=en.matricule_etudiant \n" +
                    "and ens.matricule_enseignant=en.matricule_enseignant\n" +
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
            data.add(cm);  
        }
        Encours.setItems(data);
    }
    catch(Exception e){
          e.printStackTrace();
          System.out.println("Error on Building Data");            
    }
       }
       
        public void encadrementCloturerData(){        
        data1 = FXCollections.observableArrayList();
     
     try{      
        String SQL ="select etu.nom, etu.prenom, etu.filiere, etu.niveau, en.typeencad, en.theme, en.date_debut, en.date_fin\n" +
                    "from etudiant etu, encadrement en, enseignant ens\n" +
                    "where etu.matricule_etudiant=en.matricule_etudiant \n" +
                    "and ens.matricule_enseignant=en.matricule_enseignant\n" +
                    "and en.status = 0";   
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
            data1.add(cm);  
        }
        Cloturer.setItems(data1);
    }
    catch(Exception e){
          e.printStackTrace();
          System.out.println("Error on Building Data");            
    }
       }
        
        public void creerRDVData(){
            dataRDV = FXCollections.observableArrayList();
            try{
                String request = "select * from seance";
                stm = con.ObtenirConnexion().createStatement();
                ResultSet result = stm.executeQuery(request);
                while(result.next()){
                    CreerRDV newRDV = new CreerRDV();
                    newRDV.setMatEdtRDV(result.getString("matricule_etudiant"));
                    newRDV.setMatEnseigRDV(result.getString("matricule_enseignant"));
                    newRDV.setNomSeanceRDV(result.getString("nom"));
                    newRDV.setBillanRDV(result.getString("id_bilan"));
                    newRDV.setHeureRDV(result.getString("heure_seance"));
                    newRDV.setDateRDV(result.getString("date_seance"));
                    dataRDV.add(newRDV);
                }
                creerRDV.setItems(dataRDV);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        public void initialiseComboBox(){
            bilanRDV = FXCollections.observableArrayList();
            matEtdRDV = FXCollections.observableArrayList();
            matEnsRDV = FXCollections.observableArrayList();
            
             try{
             String SQLBilan = "select b.intitule from bilan b";
             String SQLMatEtd = "select et.matricule_etudiant from etudiant et";
             String SQLMatEns = "select es.matricule_enseignant from enseignant es";
             stm=con.ObtenirConnexion().createStatement();
             ResultSet rsBilan = stm.executeQuery(SQLBilan);  
             while(rsBilan.next()){
                 Bilan b = new Bilan();
                 b.setIntitule(rsBilan.getString("intitule"));
                 bilanRDV.add(b.getIntitule());
             }
             billanRDVCB.setItems(bilanRDV);
             
             ResultSet rsMatEtd = stm.executeQuery(SQLMatEtd);
             while(rsMatEtd.next()){
                 Etudiants e = new Etudiants();
                 e.matricule.set(rsMatEtd.getString("matricule_etudiant"));
                 matEtdRDV.add(e.getMatricule());
             }
             matEdtRDVCB.setItems(matEtdRDV);
             
             ResultSet rsMatEns = stm.executeQuery(SQLMatEns);
             while(rsMatEns.next()){
                 Enseignant e = new Enseignant();
                 e.setMatricule(rsMatEns.getString("matricule_enseignant"));
                 matEnsRDV.add(e.getMatricule());
             }
             matEnseigRDVCB.setItems(matEnsRDV);
             
             vider();
         }
         catch(Exception e){
             e.printStackTrace();
          System.out.println("Error Innitialise ComboBox");  
         }

        }
        
        public void vider(){
            matEdtRDVCB.setValue("Student's Matr.");
            matEnseigRDVCB.setValue("Lecturer's Matr.");
            nomSeanceRDVTF.setText("");
            billanRDVCB.setValue("Select Intitulé");
            heureRDVTF.setText("");
            dateRDVTF.setText("");
        }
        
        @FXML
        public void programmerRDV(ActionEvent evt){
            String matEtd = matEdtRDVCB.getValue();
            String matEnseig = matEnseigRDVCB.getValue();
            String nomSeance = nomSeanceRDVTF.getText();
            String intituleBilan = billanRDVCB.getValue();
            String heure = heureRDVTF.getText();
            String date = dateRDVTF.getText();
            String idBilan = "select b.id_bilan from bilan b where b.intitule = '" + intituleBilan + "'";
            //String request="insert into seance (nom,date_seance,heure_seance,id_bilan,matricule_enseignant,"
            //        + "matricule_etudiant) values ('"+nomSeance+"','"+date+"','"+heure+"','"+idBilan+"'"+matEnseig+"','"+matEtd+"')";
            try{
                stm=con.ObtenirConnexion().createStatement();
                ResultSet rsIdBilan = stm.executeQuery(idBilan);
                Bilan b = new Bilan();
                while(rsIdBilan.next()){
                    b.setId(rsIdBilan.getInt("id_bilan"));
                }
                String request="insert into seance (nom,date_seance,heure_seance,id_bilan,matricule_enseignant,"
                    + "matricule_etudiant) values ('"+nomSeance+"','"+date+"','"+heure+"','"+b.getId()+"','"+matEnseig+"','"+matEtd+"')";
                stm.executeUpdate(request);
                creerRDVData();
                vider();
                JOptionPane.showMessageDialog(null,"Vous avez pris un Rendez le " + date);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        @FXML
        public void supprimerRDV(ActionEvent evt){
            try{
                if(JOptionPane.showConfirmDialog(null,"Attention vous allez supprimer un etudiant, etez vous sur ?","supprimer etudiant",JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION){
                    if(!matEdtRDVCB.getValue().equals("Student's Matr.") && !matEnseigRDVCB.getValue().equals("Lecturer's Matr.")){
                        stm = con.ObtenirConnexion().createStatement();
                        String query = "delete from seance where matricule_etudiant = '" + matEdtRDVCB.getValue() + "' and "
                                + "matricule_enseignant = '" + matEnseigRDVCB.getValue() + "'";
                        stm.executeUpdate(query);
                        creerRDVData();
                        vider();
                    }else{
                          JOptionPane.showMessageDialog(null,"veillez selectionner les matricules de l'étudiant et de l'enseignant à supprimer !!!");    
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,"erreur de suppression\n"+e.getMessage());
            }
        }
        
        private final ListChangeListener<CreerRDV> RDVSelectedListener = new ListChangeListener<CreerRDV>(){
            @Override
            public void onChanged(ListChangeListener.Change<? extends CreerRDV> c){
                rdvItemSelected();
            }
        };
        
        public void rdvItemSelected(){
            
        }
        
        @FXML
        public void editerRDV(MouseEvent evt){
            final ObservableList<CreerRDV> tableRDVSelected = creerRDV.getSelectionModel().getSelectedItems();
            tableRDVSelected.addListener(RDVSelectedListener);
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
    String requete="insert into annee (nom) values ('"+annee+"')";
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
    
    //Encadrement Cloturer
    nomCL.setCellValueFactory(new PropertyValueFactory("nomCL"));     
    prenomCL.setCellValueFactory(new PropertyValueFactory("prenomCL"));        
    filiereCL.setCellValueFactory(new PropertyValueFactory("filiereCL"));    
    niveauCL.setCellValueFactory(new PropertyValueFactory("niveauCL"));        
    typeCL.setCellValueFactory(new PropertyValueFactory("typeCL"));
    themeCL.setCellValueFactory(new PropertyValueFactory("themeCL"));
    datedebutCL.setCellValueFactory(new PropertyValueFactory("datedebutCL"));
    datefinCL.setCellValueFactory(new PropertyValueFactory("datefinCL"));
    
    //creer un RDV
    matEdtRDV.setCellValueFactory(new PropertyValueFactory("matEdtRDV"));
    matEnseigRDV.setCellValueFactory(new PropertyValueFactory("matEnseigRDV"));
    nomSeanceRDV.setCellValueFactory(new PropertyValueFactory("nomSeanceRDV"));
    billanRDV.setCellValueFactory(new PropertyValueFactory("billanRDV"));
    heureRDV.setCellValueFactory(new PropertyValueFactory("heureRDV"));
    dateRDV.setCellValueFactory(new PropertyValueFactory("dateRDV"));
    
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
    creerRDVData();
    initialiseComboBox();
    etudiantsEncadrerData();
    anneeData();
    }    
    
}
