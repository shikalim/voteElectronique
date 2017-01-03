package ELECTION ;

import java.io.*;
import java.net.*;
import java.util.* ;
public class ConsoleBureauDeVote {
  public  static void main(String[] args){
    try{
      DatagramSocket dso=new DatagramSocket();
      byte[]data;
      Scanner sc = new Scanner(System.in);
      for(;;){
      System.out.println("Tapez: 1=>Election chef\n 2=>GBYE Anneau 1 3=> GBYE Anneau 2 \n 4=>TEST Anneau 1\n5==> TEST Anneau 2 \n 6 <nom fichier> ==> Télécharger un fichier");
      String s = sc.nextLine();
       s+="\n";
      data=s.getBytes();
      DatagramPacket paquet=new
      DatagramPacket(data,data.length,InetAddress.getByName("localhost"),Integer.parseInt(args[0]));
      dso.send(paquet);
      }
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }
}
