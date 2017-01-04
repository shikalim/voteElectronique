import java.util.* ;
import java.io.*;
import java.net.*;

public class EcouteServeurThread extends Thread {

	private Socket sc =null;
	private Scanner scan;

	private static boolean election = false; //tester si en phase d'election ou non


	public EcouteServeurThread(Socket socket){
		sc = socket ;
	}
	
	public void run(){
		try{
			
			BufferedReader read = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			PrintWriter wr= new PrintWriter(new OutputStreamWriter(sc.getOutputStream()));

			while(true){
				
				String message = read.readLine();
				switch (message) {
					case "COMMENCE" : 
						election = true;

					case "FIN" : 
						election = false;

					case "DEJAVOTE" : 
						System.out.println("Vous avez déjà voté");

					case "PASINSCRIT" :
						System.out.println("Vous n'êtes pas inscrit");

					case "ACKVOTE" : 
						System.out.println("Votre vote a été pris en compte");

					default :
				}
			}
		}catch(Exception e){
			System.err.println("Erreur : " + e);
		}finally{
			try{
				sc.close();}catch(Exception e){}
			}
		}
	}
