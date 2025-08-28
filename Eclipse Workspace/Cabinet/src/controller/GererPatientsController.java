package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class GererPatientsController implements Initializable {
	
	@FXML
    private AnchorPane root;
	
	private Parent fxml;

    @FXML
    void ajouter(MouseEvent event) {
    	
    	try {
			fxml = FXMLLoader.load(getClass().getResource("/interfacess/AjouterPatients.fxml"));
			root.getChildren().removeAll();
			root.getChildren().setAll(fxml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    @FXML
    void dossier(MouseEvent event) {
    	
    	try {
			fxml = FXMLLoader.load(getClass().getResource("/interfacess/DossiersM.fxml"));
			root.getChildren().removeAll();
			root.getChildren().setAll(fxml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
