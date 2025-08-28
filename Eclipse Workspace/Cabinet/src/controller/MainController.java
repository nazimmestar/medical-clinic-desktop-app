package controller;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import client.Client;


public class MainController implements Initializable{
	
	
    private Client client;
	
	
	@FXML
    private VBox VBox;

    @FXML
    private Button btn_seconnecter;

    @FXML
    private Button btn_sincrire;
    
    private Parent fxml;
    
    @FXML
    void close(MouseEvent event) {
    	
    	
    	this.client.close();
    	System.exit(0);
    	


    }

    
    @FXML
    void openSignIn() {
    	
    	TranslateTransition t = new TranslateTransition(Duration.seconds(1),VBox);
		t.setToX(VBox.getLayoutX() * 20);
		t.play();
		t.setOnFinished(e -> 
		{
			try {
				fxml= FXMLLoader.load(getClass().getResource("/interfacess/Signin.fxml"));
				VBox.getChildren().removeAll();
				VBox.getChildren().setAll(fxml);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
					
		} );

    }

    @FXML
    void openSignUp() {
    	TranslateTransition t = new TranslateTransition(Duration.seconds(1),VBox);
    	t.setToX(5);
    	t.play();
    	t.setOnFinished(e -> 
		{
			try {
				fxml= FXMLLoader.load(getClass().getResource("/interfacess/SignUp.fxml"));
				VBox.getChildren().removeAll();
				VBox.getChildren().setAll(fxml);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} );

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		this.client = new Client() ;
		
		TranslateTransition t = new TranslateTransition(Duration.seconds(1),VBox);
		t.setToX(VBox.getLayoutX() * 20);
		t.play();
		t.setOnFinished(e -> 
		{
			try {
				fxml= FXMLLoader.load(getClass().getResource("/interfacess/Signin.fxml"));
				VBox.getChildren().removeAll();
				VBox.getChildren().setAll(fxml);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			
		} );
		
	}

}
