package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import application.ConnexionMysql;



class ClientHandler extends Thread { 
	
	
 
 final Socket socket; 
 final BufferedReader input;
 final PrintWriter output; 

 public ClientHandler(Socket socket, BufferedReader input, PrintWriter output){ 
     this.socket = socket; 
     this.input = input; 
     this.output = output; 
 } 

 public void run(){ 
     boolean loop = true;
     while (loop){ 
       try { 
       	 
       	 String message = (String) input.readLine();
         if(message.equalsIgnoreCase("Exit")) {  
           System.out.println("Client " + this.socket + " envoie exit..."); 
           System.out.println("Fermeture de cette connexion."); 
           System.out.println("Connection ferm�e"); 
           loop = false; 
           this.socket.close();	   this.input.close();    this.output.close();
         }else{
        	 
        	 
            	//output.println(message.toUpperCase());
            	 
            	 
         } 
       } catch (IOException e) { 
             e.printStackTrace(); 
       } 
     }//end of while (loop){   
 }//end of run() 
} //end of ClientHandler 