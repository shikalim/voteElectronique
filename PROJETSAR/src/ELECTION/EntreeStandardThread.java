package ELECTION;

import java.io.*;
import java.net.*;

public class EntreeStandardThread extends Thread {

  private BureauDeVote bureau ;
  private DatagramSocket dso ;
  byte[]data;
  DatagramPacket paquet;

  public EntreeStandardThread(BureauDeVote bureau){

    try{
      this.bureau = bureau;
      bureau.setPortEntree();
      System.out.println("Mon port d'écoute pour l'entrée standard: "+bureau.getPortEntree());
      dso=new DatagramSocket(bureau.getPortEntree());
      data=new byte[1024];
      paquet=new DatagramPacket(data,data.length);
    }
    catch(Exception e )
    {
      e.printStackTrace();
      return ;
    }

  }

  public void run(){


    while(true)
    {
      try
      {
        dso.receive(paquet);
        String st=new String(paquet.getData(),0,paquet.getLength());

        System.out.println("============================================================");
        System.out.println("                         PORT UDP ENtreé standard                           ");
        System.out.println("============================================================");
        System.out.println("J'ai reçu un mesage depuis l'entrée standard:"+st);
        System.out.println("De la machine  "+paquet.getAddress().toString());
        if(st.equals("1\n")){
            if(bureau.getVote())
            {
              System.out.println("J'ai déjà voté :(");
              continue ;
            }
            System.out.println("Mon numéro pour l'élection: "+bureau.getNumero());
            ChangRoberts.provoquerElection(bureau);
        }

      }
        catch(Exception e){
          e.printStackTrace();
        }
    }

  }

}
