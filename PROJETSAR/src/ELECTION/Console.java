package ELECTION;

import java.io.*;
import java.util.*;
import java.net.*;

public class Console{
	public static void main (String[] args) throws InterruptedException{
		int port=4242;;
		InetAddress hote=null;
		Socket sc=null;
        BufferedReader in ;
        PrintWriter out;
		try{
			if (args.length==2){
				hote = InetAddress.getByName(args[0]);
				port = Integer.parseInt(args[1]);
			} 
			else{
				//throw new IllegalArgumentException("Veuillez saisir une adresse IP et un port.");
                hote = InetAddress.getByName("localhost");
			}
		}
		catch(UnknownHostException e){
			System.err.println("Machine inconnue :" +e);
		} 

		try{
			System.out.println("La console est prête.");
                  //System.out.println("socket :"+hote+" and port :" +port);
			sc = new Socket(hote,port);
			ClientServeurThread cst = new ClientServeurThread(sc);
			cst.start();
			cst.join();

			System.out.println("Client finished");
		}
		catch(IOException e){
			System.err.println("Impossible de creer la socket du client : " +e); 
		}
		finally{
			try{
				sc.close();
			}
			catch (IOException e){}
		}
	}
}
