package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javafx.scene.*;
import application.ConnexionMysql;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.util.*;
import javax.mail.*;
import javafx.fxml.Initializable;

public class SigninController implements Initializable{
	    
	    Connection cnx;
	    public PreparedStatement st;
	    public ResultSet result ;     
	
	    @FXML
	    private TextField txt_user; // import javafx.scene.control.TextField;

	    @FXML
	    private PasswordField txt_pass;

	    @FXML
	    private Button btn_reset;

	    @FXML
	    private Button btn_connecter;
	    
	    @FXML
	    private VBox VBox;
	    
	    private Parent fxml;
	    

	    @FXML
	    void SendEmail() {
	    	
	    	String sql ="select * from admin where userName ='"+txt_user.getText()+"'";
	    	String email="empty";
	    	
	    	try {
				st=cnx.prepareStatement(sql);
				result=st.executeQuery();
				if(result.next()) {
					
					email=result.getString("email");
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	
	    	sendMail(email);
	    	
	    }

	    @FXML
	    void openHome() {
	    	
	    	String user = txt_user.getText();
	    	String pass = txt_pass.getText();
	    	String sql ="select userName, password, id, email, image from admin";
	    	int nb=0;
	    	try {
				st=cnx.prepareStatement(sql);
				result=st.executeQuery();
				
				while(result.next()) {
					

			    	if(user.equals(result.getString("userName"))&&pass.equals(result.getString("password"))) {
			    		nb=1;
			    		String sql2="insert into userconnect( userName, email, password, image) values(?,?,?,?)";
			    		st=cnx.prepareStatement(sql2);
			    		st.setString(1, result.getString("userName"));
			    		st.setString(2, result.getString("email"));
			    		st.setString(3, result.getString("password"));
			    		st.setBlob(4, result.getBlob("image"));
			    		st.executeUpdate();
			    		
			    		System.out.println("CORRECET !");
			    		VBox.getScene().getWindow().hide();
			    		Stage home =new Stage();
			    		try {
							fxml = FXMLLoader.load(getClass().getResource("/interfacess/ok.fxml"));
							Scene scene = new Scene(fxml);
							home.setScene(scene);
							home.show();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			    		
			    	}
				            	
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	
	    	if(nb==0){
	    		Alert alert =new Alert(AlertType.ERROR,"nom de l'utilisateur ou mot de passe incorect !", javafx.scene.control.ButtonType.OK);
	    		alert.showAndWait();
	    		txt_user.setText("");
	    		txt_pass.setText("");
	    	}
	    	
	    }
	    
	    public void sendMail(String recepient) {
	    	System.out.println("preparing to send email.....");
	    	Properties properties = new Properties();
	    	
	    	//enable authentication
	    	properties.put("mail.smtp.auth", "true");
	    	//set TLS encryption enabled
	    	properties.put("mail.smtp.starttls.enable", "true");
	    	//set SMTP host
	    	properties.put("mail.smtp.host", "smtp.gmail.com");
	    	//set smtp port
	    	properties.put("mail.smtp.port", "587");
	    	//your gmail adresse
	    	final String MyAccountEmail="cabinetmedical095@gmail.com";
	    	//your gmail password
	    	final String password="cabinet123";
	    	
	    	//create a session with account credentials
	    	Session session = Session.getDefaultInstance(properties, new Authenticator(){
	    		protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
	    			
	    			return new javax.mail.PasswordAuthentication(MyAccountEmail,password);
	    			
	    		}
	    	} );
	    	
	    	//prepare email message
	    	Message message =preparedMessage(session, MyAccountEmail, recepient);
	    	//send email
	    	try {
				Transport.send(message);
				Alert alert =new Alert(AlertType.CONFIRMATION,"Consulter votre boite mail !", javafx.scene.control.ButtonType.OK);
	    		alert.showAndWait();
				
			} catch (MessagingException e) {
				e.printStackTrace();
			}
	    	
	    	
	    	
	    }
	    
	    @SuppressWarnings("unused")
		private Message preparedMessage(Session session, String MyAccountEmail, String recepient) {
	    	String sql ="select * from admin where userName ='"+txt_user.getText()+"'";
	    	String pass="empty";
	    	try {
				st=cnx.prepareStatement(sql);
				result=st.executeQuery();
				if(result.next()) {
					pass =result.getString("password");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	
	    	String text="votre mot de passe est  :  "+pass+"";
	    	String objet="Recuperation de mot de passe";
	    	Message message = new MimeMessage(session);
	    	try {
				message.setFrom(new InternetAddress(MyAccountEmail));
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
				message.setSubject(objet);
				String htmlcode ="<h1> "+text+" </h1> <h2><b> </b></h2>";
				message.setContent(htmlcode, "text/html");
				return message;
			} catch (MessagingException e) {
				e.printStackTrace();
			}
	    	
	    	
	    	return null;
	    }
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		cnx =ConnexionMysql.connexionDB();	
		
	}

}
