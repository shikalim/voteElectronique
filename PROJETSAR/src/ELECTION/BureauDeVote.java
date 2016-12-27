package ELECTION;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;



public class BureauDeVote {
	
	ArrayList<Electeur> electeur = new ArrayList<Electeur>();
	ArrayList<Candidat> candidat = new ArrayList<Candidat>();
	
	
	
	
	public static void main(String[] args) throws IOException  {
	
		ServeurDeVote serveur = new ServeurDeVote();
		Socket socket = null;
		PrintWriter pw = null;
		BufferedReader br = null;
		
		if (args.length==2){
			
			int port =0;
			String adress;
			
			try {
				port = Integer.parseInt(args[1]) ;
			}catch (NumberFormatException e) {
				e.printStackTrace();
			} 
				
			adress=args[0];
			
			try {
				socket =new Socket(adress,port);
				
				br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
				pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
				
				String message="Salut je suis un nouveau bureau de vote!Je vais te passer mes " +
						"coordonnées et toi \n passe moi les coordonnées du bureau avec qui tu communique." +
						"Comme ça je pourrai m'insèrer \n dans le anneau de communication. \n Merci \n";
				pw.print(message);
				pw.flush();
				
				message="COOR "+serveur.getAdress()+" "+serveur.getUdpPort()+"\n";
				pw.print(message);
				pw.flush();
				
				
				message=br.readLine();
				System.out.println("merci, je viens de recevoir les coordonnées");
				
				String coordonnées[]  = message.split(" ");
				String nextAdress =coordonnées[0];
				int nextUdpPort = Integer.parseInt(coordonnées[1]);
				
				serveur.setServeurDeVote1(nextUdpPort, nextAdress);
				
			}catch(Exception e){
				System.out.println(e);
				e.printStackTrace();
			}finally{
				socket.close();
				br.close();
				pw.close();
			}
		
		}
	
		try{
			 ServerSocket ssv=new ServerSocket(serveur.getTcpPort());
			 DatagramSocket ds=new DatagramSocket(serveur.getUdpPort());
			 serveur.setServeurDeVote2(ds);
			 
			 
			 while(true){
				 Socket newSocket=ssv.accept();
				 
				 Thread tcp=new ComTcpThread(newSocket);
				 Thread udp=new ComUdpThread(serveur);
				 
				 Thread entreeTcp=new EntreeTcpThread(newSocket);
				 Thread entreeUdp=new EntreeUdpThread(serveur);
				 
				 tcp.start();
				 udp.start();
				 entreeTcp.start();
				 entreeUdp.start();
				 
				 tcp.join();
				 udp.join();
				 entreeTcp.join();
				 entreeUdp.join();
				
			
				
				
				
			}
			
		}catch(Exception e){
			   System.out.println(e);
			 e.printStackTrace();
			 }

	}

	
	

}
