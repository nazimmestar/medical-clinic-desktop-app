package controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.ConnexionMysql;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import models.Medicament;
import models.Patients;

public class ListeMedicaments implements Initializable {
	
	Connection cnx;
    public PreparedStatement st;
    public ResultSet result ;
    
    private FileInputStream fs;
	
	    @FXML
	    private TextField txt_mdc;

	    @FXML
	    private TextField txt_grp;

	    @FXML
	    private TextField txt_cmp;

	    @FXML
	    private TextField txt_dose;

	    @FXML
	    private ImageView image_mdc;

	    @FXML
	    private Label lab_url;

	    @FXML
	    private TableView<Medicament> table_mdc;
	    
	    @FXML
	    private TableColumn<Medicament, Integer> cln_id;

	    @FXML
	    private TableColumn<Medicament, String> cln_nom;

	    @FXML
	    private TableColumn<Medicament, String> cln_groupe;

	    @FXML
	    private TableColumn<Medicament, String> cln_composition;

	    @FXML
	    private TableColumn<Medicament, String> cln_dose;

	    @FXML
	    private TextField txt_search;
	    
	    public ObservableList<Medicament> data = FXCollections.observableArrayList();


	    @FXML
	    void ajouter(MouseEvent event) {
	    	String mdc = txt_mdc.getText();
	    	String grp = txt_grp.getText();
	    	String cmp = txt_cmp.getText();
	    	String dose = txt_dose.getText();
	    	
	    	File image =new File(lab_url.getText());
	    	
	    	String sql="insert into medicaments (denom,groupe,compo,dose,image) values(?,?,?,?,?)";
	    	if(!mdc.equals("")&&!grp.equals("")&&!cmp.equals("")&&!dose.equals("")) {
	    		try {
					st=cnx.prepareStatement(sql);
					st.setString(1, mdc);
					st.setString(2, grp);
					st.setString(3, cmp);
					st.setString(4, dose);
					fs =new FileInputStream(image);
					st.setBinaryStream(5, fs, image.length());
					st.execute();
					txt_mdc.setText("");
					txt_grp.setText("");
					txt_cmp.setText("");
					txt_dose.setText("");
					Alert alert = new Alert(AlertType.CONFIRMATION,"medicament ajouté avec succès!",javafx.scene.control.ButtonType.OK);
				    alert.showAndWait();
				    showMdc();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
	    		
	    	}


	    }

	    @FXML
	    void imprter_image(MouseEvent event) {
	    	
	    	FileChooser fc = new FileChooser();
	    	fc.getExtensionFilters().add(new ExtensionFilter("Image Files","*.png","*.jpg"));
	    	File f=fc.showOpenDialog(null);
	    	if (f!= null) {
	    		lab_url.setText(f.getAbsolutePath());
	    		Image image = new Image(f.toURI().toString(),image_mdc.getFitWidth(),image_mdc.getFitHeight(),true,true);
	    		image_mdc.setImage(image);
	    	}

	    }

	    @FXML
	    void modifier(MouseEvent event) {
	    	
	    	String mdc = txt_mdc.getText();
	    	String grp = txt_grp.getText();
	    	String cmp = txt_cmp.getText();
	    	String dose = txt_dose.getText();
	    	
	    	File image =new File(lab_url.getText());
	        Medicament selectedMDC = table_mdc.getSelectionModel().getSelectedItem();
	    	String sql="update medicaments set denom=?,groupe=?,compo=?,dose=?,image=? where idM ='"+selectedMDC.getId()+"'" ;
	    	try {
				st=cnx.prepareStatement(sql);
				st.setString(1, mdc);
				st.setString(2, grp);
				st.setString(3, cmp);
				st.setString(4, dose);
				fs =new FileInputStream(image);
				st.setBinaryStream(5, fs, image.length());
				st.executeUpdate();
				showMdc();
				lab_url.setText("aucune image sélectionnée");
				st.execute();
				txt_mdc.setText("");
				txt_grp.setText("");
				txt_cmp.setText("");
				txt_dose.setText("");
				image_mdc.setImage(null);
				Alert alert = new Alert(AlertType.CONFIRMATION,"medicament modifié avec succès!",javafx.scene.control.ButtonType.OK);
			    alert.showAndWait();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

	    }

	    
	    
	    @FXML
	    void search(MouseEvent event) {
	    	
	    	String searchMDC = txt_search.getText();
	    	if(searchMDC !=null) {
		        ObservableList<Medicament> filteredList = data.filtered(MDC -> MDC.getDenom().equalsIgnoreCase(searchMDC));
		        
		        if (!filteredList.isEmpty()) {
		            table_mdc.setItems(filteredList);
		            table_mdc.getSelectionModel().selectFirst(); // Select the first matching row
		        } else {
		            table_mdc.getItems().clear(); // Clear the table if no matching rows
		            Alert alert = new Alert(AlertType.ERROR, "Aucun medicament trouvé avec le nom = " + searchMDC, ButtonType.OK);
		            alert.showAndWait();
		        }

		    }
		    	else{
		    		Alert alert = new Alert(AlertType.ERROR, "veuillez entrez un medicament", ButtonType.OK);
		            alert.showAndWait();
		    	}

	    }
	    
	    @FXML
	    void finish(MouseEvent event) {
	    	
	    	String a = txt_search.getText();
	    	if(a=="") {
	    		clearFields();
	    	}
	    	txt_search.setText(""); // Clear the search field
	        table_mdc.getSelectionModel().clearSelection(); // Clear any row selection
	    	data.clear();
	    	showMdc();
	        clearFields();

	    }
	    
	    private void clearFields() {
	        txt_mdc.setText("");
	        txt_grp.setText("");
	        txt_cmp.setText("");
	        txt_dose.setText("");

	    }

	    @FXML
	    void supprimer(MouseEvent event) {
	    	
	        Medicament selectedMDC = table_mdc.getSelectionModel().getSelectedItem();
	        String sql="delete from medicaments where idM ='"+selectedMDC.getId()+"'" ;
	        try {
				st=cnx.prepareStatement(sql);
				st.executeUpdate();
				showMdc();
				lab_url.setText("aucune image sélectionnée");
				st.execute();
				txt_mdc.setText("");
				txt_grp.setText("");
				txt_cmp.setText("");
				txt_dose.setText("");
				image_mdc.setImage(null);
				Alert alert = new Alert(AlertType.CONFIRMATION,"medicament modifié avec succès!",javafx.scene.control.ButtonType.OK);
			    alert.showAndWait();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	        


	    }

	
	
	    public void showMdc() {
	    	table_mdc.getItems().clear();
	    	String sql ="select * from medicaments";
	    	try {
				st=cnx.prepareStatement(sql);
				result=st.executeQuery();
				while(result.next()) {
					data.add(new Medicament(result.getInt("idM"),result.getString("denom"),result.getString("groupe"),result.getString("compo"),result.getString("dose")));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
	        cln_id.setCellValueFactory(new PropertyValueFactory<Medicament, Integer>("id"));
	        cln_nom.setCellValueFactory(new PropertyValueFactory<Medicament, String>("denom"));
	        cln_groupe.setCellValueFactory(new PropertyValueFactory<Medicament, String>("groupe"));
	        cln_composition.setCellValueFactory(new PropertyValueFactory<Medicament, String>("comp"));
	        cln_dose.setCellValueFactory(new PropertyValueFactory<Medicament, String>("dose"));
	        table_mdc.setItems(data);



	    }
	    
	    
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		cnx = ConnexionMysql.connexionDB();
		showMdc();
		
		table_mdc.setOnMouseClicked(event -> {
	        Medicament selectedMDC = table_mdc.getSelectionModel().getSelectedItem();
	        String sql="select idM,denom,groupe,compo,dose,image from medicaments where idM ='"+selectedMDC.getId()+"'" ;
	        
	        byte byteImg[];
			Blob blob;
	    	try {
				st=cnx.prepareStatement(sql);
				result=st.executeQuery();
				if(result.next()) {
					txt_mdc.setText(result.getString("denom"));
					txt_grp.setText(result.getString("groupe"));
					txt_cmp.setText(result.getString("compo"));
					txt_dose.setText(result.getString("dose"));
					blob=result.getBlob("image");
					byteImg=blob.getBytes(1, (int) blob.length());
					Image img = new Image(new ByteArrayInputStream(byteImg),image_mdc.getFitWidth(),image_mdc.getFitHeight(),true,true);
					image_mdc.setImage(img);
					
				}
			
	    	} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    });
		
	}

}
