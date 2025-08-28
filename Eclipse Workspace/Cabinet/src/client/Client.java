package client;

import java.io.*; 
import java.net.*; 


public class Client{ 
	
	private InetAddress ip;
	private BufferedReader input;
	private PrintWriter output;
	private Socket socket;
	
	
	public Client() {

	     try{ 
	         this.ip = InetAddress.getByName("localhost"); 
	         this.socket = new Socket(ip, 1080); 
	         this.input = new BufferedReader( new 
								InputStreamReader(socket.getInputStream())); 
	                  
	         this.output = new PrintWriter(socket.getOutputStream(), true);
	         
	     } catch(Exception e){  
	         e.printStackTrace(); 
	     } 
	   }

	
	
	
	/**
	 * Send a request to the server and receive the response
	 * @param requete
	 * @return
	 */
	
	public String sendRequest(String request){
			
		
		String res = "";
		try {
			output.println(request);
			res = (String) input.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res; 
		
	}
	
		

 public void close(){
	this.sendRequest("exit");
	try {
		this.socket.close();
		this.input.close();
		this.output.close();	
	} catch (IOException e) {
		e.printStackTrace();
	}
	 
	
	 
 }
	
	
}//end of Client  