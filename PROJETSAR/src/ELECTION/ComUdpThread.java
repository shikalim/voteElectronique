package ELECTION;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;

public class ComUdpThread extends Thread {
	
	ServeurDeVote serveur;
	
	public ComUdpThread(ServeurDeVote serveur){

		this.serveur=serveur;
	}

	
	public void run(){
			
		try {
			byte[] data=new byte[100];
			
			while(true){
		
				DatagramPacket paquet=new DatagramPacket(data,data.length);
		
				String message=new String(paquet.getData(),0,paquet.getLength());
				System.out.println("J'ai reçu :"+message);

				data=message.getBytes();
        
				InetSocketAddress ia=new  InetSocketAddress(serveur.getNextAdress(),serveur.getNextPort());
				DatagramPacket paquet1=new DatagramPacket(data,data.length,ia);
        
			serveur.getPaquet().send(paquet1);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
		
		
		
		
	}
	
	
	
	
}
