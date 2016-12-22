package ELECTION;

import java.net.*;
import java.io.*;
import java.util.*;

public class Console{
      public static void main (String[] args){
            int port=null;;
            InetAddress hote=null;
            Socket sc=null;
            BufferedReader in;
            PrintWriter out;
           
            try{
                  if (args.length==2){
                        hote = InetAddress.getByName(args[0]);
                        port = Integer.parseInt(args[1]);
                  } 
                  else{
                        System.out.println("Merci d'indiquer l'IP du serveur ainsi que son port.")
                  }
            }
            catch(UnknownHostException e){
                  System.err.println("Machine inconnue :" +e);} 
            try{

              	/*System.out.println("La console de vote est prête");
               	//System.out.println("socket :"+hote+" and port :" +port);
              		 
               	sc = new Socket(hote,port);
               	in = new BufferedReader( new InputStreamReader(
        	    sc.getInputStream()));
          	    out = new PrintWriter(sc.getOutputStream(),true);

       		    for(int i = 0; i<counterMsg;i++){
   	                System.out.println("Enter the message you want to send :");
      	            Scanner scn = new Scanner(System.in);	
                    String req = scn.nextLine();
                    out.println(req);    
                	out.flush();
     		      	System.out.println("I send to the server : " + req);

                    String rep=in.readLine();
                    System.out.println("The server answers me : "+ rep);
                }*/

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