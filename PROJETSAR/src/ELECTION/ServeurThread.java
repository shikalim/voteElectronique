package ELECTION;

import java.net.*;
import java.util.Scanner;
import java.io.*;


public class ServeurThread extends Thread {

	ServeurDeVote serveur;

	public ServeurThread(ServeurDeVote serveur){
		this.serveur = serveur;
	}
	
	
	
	 public void run(){
		 
		 BufferedReader	br = null;
		 PrintWriter	pw = null;
		 Socket socket = null;
		 
		try{
					
			while(true){
			
			socket=serveur.getServerSocket().accept();
				
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
				
			String mess=br.readLine();
			System.out.println("Message recu :"+mess);
			}
			
			}catch(Exception e){
				System.out.println(e);
				e.printStackTrace();
			}finally{
				
				try {
					br.close();
					pw.close();
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
				
	
			}
			 
	 }
			
	
	
	
	}
	


