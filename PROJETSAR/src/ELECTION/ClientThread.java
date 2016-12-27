package ELECTION;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread{

	private String adress;
	private int port;
	private Socket socket;
	BufferedReader br;
	PrintWriter pw;
	
	public ClientThread(Socket socket){
		this.socket=socket;
	}
	
	public void run(){
		
		try{
		  br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		  pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		
		 
		 String message=br.readLine();
		 System.out.println("message reçu : "+message );		 
		 
		 message="COOR";
		 pw.print(message);
		 pw.flush();
			
			
						
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally{
			
			try {
				pw.close();
				br.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
		
		
	}
	
	
	
	
}
