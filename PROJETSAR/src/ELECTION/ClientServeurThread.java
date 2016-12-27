package ELECTION;

import java.net.*;
import java.io.*;
import java.util.*;

/* Cette classe est le thread appelé par le client afin de communiquer avec le serveur*/

public class ClientServeurThread extends Thread{
	Socket sc;
	String name;
   	PrintWriter out;
	BufferedReader in;

	public ClientServerThread(Socket sc){
		this.sc=sc;
	}

	public void run(){
		try{
			in = new BufferedReader( new InputStreamReader(
	        sc.getInputStream()));
	        out = new PrintWriter(sc.getOutputStream(),true);
			System.out.println("Le thread est lancé");

			System.out.println("Bienvenue dans la console de vote");

			boolean boucle = true;
			while(boucle){
				Scanner sc = new Scanner(System.in);
			   	System.out.print("Envoyer au serveur : ");
		   		String reponse = sc.nextLine();
		   		out.println(reponse);
			}
		}
        catch (IOException e){
    	    System.err.println("Erreur : " +e);
		}
		finally{
   	      try{  
            	sc.close();
         	}
            	catch (IOException e){}
            }
	}
}
