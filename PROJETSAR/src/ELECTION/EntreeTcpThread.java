package ELECTION;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EntreeTcpThread extends Thread{

	Socket socket;
	
	
	public EntreeTcpThread(Socket socket){
		this.socket=socket;
	}
	
	
	
	public void run(){

		PrintWriter pw=null;
		
		try{
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			while (true){
			
			Scanner sc =new Scanner(System.in);
			String lu=sc.nextLine();
			
			
			pw.print(lu);
			pw.flush();
			}
	
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}finally{
			
			try {
				pw.close();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
 }
}