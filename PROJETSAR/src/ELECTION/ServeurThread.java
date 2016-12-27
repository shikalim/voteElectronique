import java.net.*;
import java.io.*;
import java.util.*;

<<<<<<< HEAD
public class ServeurThread{

      public static void main(String[] args){
            int port=4242;
            ServerSocket se;
            Socket ssv=null;
            PrintWriter out;
            BufferedReader in;

            try{
                  se = new ServerSocket(port);
                  System.out.println("Le serveur est prêt");
                  ssv = se.accept();
                  in = new BufferedReader(
                        new InputStreamReader(ssv.getInputStream()));
                  out = new PrintWriter(ssv.getOutputStream(), true);

                  
                  while(true){
                        String req = in.readLine();
                        System.out.println("Client says : " + req);


                        /*System.out.println("Enter the message you want to send :");
                        Scanner scn = new Scanner(System.in);
                        String rep = scn.nextLine();
                        out.println(rep);
                        System.out.println(" I answer :" + rep);*/
                  }

                  //System.out.println("Server finished");
            }
            catch (IOException e){
                  System.err.println("Erreur : " +e);
            }
            finally{
                  try{  
                        ssv.close();
                  }
                  catch (IOException e){}
            }
      }
}
=======
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
	


>>>>>>> 580517a4abeb04e1da9401d699032ccf64bafd40
