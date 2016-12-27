package ELECTION;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

public class ServeurDeVote {
	
	

	private String id;
	private int tcpPort;
	private int udpPort;
	private int nextUdpPort;
	private String myAdress;
	private String nextAdress;
	private DatagramSocket ds;
	
	public ServeurDeVote(){
		
		this.myAdress=myAdress();
		this.tcpPort=tcpPort(this.myAdress);
		this.udpPort=udpPort();
		this.id=myId(tcpPort,myAdress);
		this.nextUdpPort=udpPort;
		this.nextAdress=myAdress;
	}
	
	public String getId(){
		return this.id;
	}
	
	public int getTcpPort(){
		return this.tcpPort;
	}
	
	public int getUdpPort(){
		return this.udpPort;
	}
	
	public int getNextPort(){
		return this.nextUdpPort;
	}

	public String getAdress(){
		return this.myAdress;
	}
	
	public String getNextAdress(){
		return this.nextAdress;
	}
	
	public DatagramSocket getPaquet() {
		return ds;
	}
	
	public void setServeurDeVote1(int nextUdpPort,String nextAdress){
		this.nextUdpPort=nextUdpPort;
		this.nextAdress=nextAdress;
	}
	
	public void setServeurDeVote2(DatagramSocket ds){
		this.ds=ds;
	}
	
	private int tcpPort(String c){

		for(int i=1024;i<10000;i++){
			try{
				Socket socket=new Socket(c,i);
				socket.close();
			}catch(Exception e){
				return i;
			}
		}
		return -1;
		}

	private int udpPort(){

		for(int i=1024;i<10000;i++){ 
			try{
				DatagramSocket dso=new DatagramSocket(i);
				dso.close();
				return i;
			  
			}catch(Exception e){
				return -1;
			}
		 }
		return -1;
	}
	
	private String myAdress() {
		
		String s = null;
		Enumeration<NetworkInterface> listNi;
		try {
			listNi = NetworkInterface.getNetworkInterfaces();
		
	 	while(listNi.hasMoreElements()){
			NetworkInterface nic=listNi.nextElement();
			Enumeration<InetAddress> listIa=nic.getInetAddresses();
			while(listIa.hasMoreElements()){
	 			InetAddress iac=listIa.nextElement();
				if(iac instanceof Inet4Address && !iac.isLoopbackAddress())
					{ s=iac.toString();
					String tab[]=s.split("\\/");
					return tab[1];
					}
				}
	 		}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
				
		return null;

	}
	
	
	private String remplirZero(int n,String s){
		
		String str="";
		for(int i=0;i<n-s.length();i++)
			str+="0";
		
		return str+s;
	}

	
	public String myId(int tcpPort,String adress){

		String tab[]=adress.split("\\.");
		String s=remplirZero(3,tab[3]);

		return Character.toString(tab[2].charAt(tab[2].length()-1))+s+Integer.toString(tcpPort);
		}

	public String toString(){
		return "Mon identifiant : "+id+"\nMon port TCP : "+tcpPort+"\nMon port UDP : "+udpPort+"\nLe port du serveur avec qui"+
				"je communique :"+nextUdpPort+"\n Mon adresse :"+myAdress+"\nL'adresse du"+
				"avec qui je communique :"+nextAdress;
	}

	
	






}





