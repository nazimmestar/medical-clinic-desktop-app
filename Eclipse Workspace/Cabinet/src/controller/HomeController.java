package controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.ConnexionMysql;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class HomeController implements Initializable{
	
	Connection cnx;
    public PreparedStatement st;
    public ResultSet result ;   
	
	private Parent fxml;
	
	@FXML
    private ImageView image_user;

    @FXML
    private Label lab_user;

	
	@FXML
    private AnchorPane root;
	
	@FXML
    void accueil(MouseEvent event) {
		try {
			fxml = FXMLLoader.load(getClass().getResource("/interfacess/Accueil.fxml"));
			root.getChildren().removeAll();
			root.getChildren().setAll(fxml);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }


    @FXML
    void gererP(MouseEvent event) {
    	
    	try {
			fxml = FXMLLoader.load(getClass().getResource("/interfacess/GererPatients.fxml"));
			root.getChildren().removeAll();
			root.getChildren().setAll(fxml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }
    
    @FXML
    void gererA(MouseEvent event) {
    	
    	try {
			fxml = FXMLLoader.load(getClass().getResource("/interfacess/GererAgenda.fxml"));
			root.getChildren().removeAll();
			root.getChildren().setAll(fxml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    

    @FXML
    void listteA(MouseEvent event) {
    	
    	try {
			fxml = FXMLLoader.load(getClass().getResource("/interfacess/GererListeAttente.fxml"));
			root.getChildren().removeAll();
			root.getChildren().setAll(fxml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    
    @FXML
    void traitement(MouseEvent event) {
    	
    	try {
			fxml = FXMLLoader.load(getClass().getResource("/interfacess/Prescreption.fxml"));
			root.getChildren().removeAll();
			root.getChildren().setAll(fxml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    

    @FXML
    void medicaments(MouseEvent event) {
    	
    	try {
			fxml = FXMLLoader.load(getClass().getResource("/interfacess/ListeMedicaments.fxml"));
			root.getChildren().removeAll();
			root.getChildren().setAll(fxml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    @FXML
    void exit(MouseEvent event) {
    	
    	System.exit(0);

    }




	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		cnx =ConnexionMysql.connexionDB();	
		String sql="select userName,image from userconnect where id =(select MAX(id) from userconnect)";
		byte byteimg[];
		Blob blob;
		try {
			st=cnx.prepareStatement(sql);
			result=st.executeQuery();
			if(result.next()) {
				lab_user.setText(result.getString("userName"));
				blob =result.getBlob("image");
				byteimg=blob.getBytes(1, (int) blob.length());
				Image img = new Image(new ByteArrayInputStream(byteimg),image_user.getFitWidth(),image_user.getFitHeight(),true,true);
				image_user.setImage(img);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		try {
			fxml = FXMLLoader.load(getClass().getResource("/interfacess/Accueil.fxml"));
			root.getChildren().removeAll();
			root.getChildren().setAll(fxml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
