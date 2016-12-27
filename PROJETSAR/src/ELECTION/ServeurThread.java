import java.net.*;
import java.io.*;
import java.util.*;

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