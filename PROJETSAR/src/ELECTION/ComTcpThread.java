package ELECTION;

import java.net.*;
import java.util.Scanner;
import java.io.*;


public class ComTcpThread extends Thread {

	Socket socket;
	

	public ComTcpThread(Socket socket){
		this.socket=socket;
	}
	
	
	
	 public void run(){
		 
		 BufferedReader	br = null;
		 PrintWriter	pw = null;
	
		try{
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
					
			while(true){
				
				String mess=br.readLine();
				System.out.println("Message recu :"+mess);
			
				pw.print("Hello\n");
				pw.flush();
			
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
	


