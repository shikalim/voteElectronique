package ELECTION;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class EntreeUdpThread extends Thread {

	
	ServeurDeVote serveur;
	
	public EntreeUdpThread(ServeurDeVote serveur){
		this.serveur=serveur;
	}

	
	public void run(){
			
		try {
			
			byte[] data=new byte[100];
			
			while(true){
				
				Scanner sc =new Scanner(System.in);
				String lu=sc.nextLine();
				
				data=lu.getBytes();
				InetSocketAddress ia=new  InetSocketAddress(serveur.getNextAdress(),serveur.getNextPort());
				DatagramPacket paquet=new DatagramPacket(data,data.length,ia);
				serveur.getPaquet().send(paquet);
			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
		
		
