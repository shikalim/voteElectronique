package ELECTION;

import java.util.Hashtable;
import java.io.*;
import java.net.*;

public class EntreeUdpThread extends Thread {

  private BureauDeVote bureau ;
  private DatagramSocket dso ;
  byte[] data;
  DatagramPacket paquet;

  public EntreeUdpThread (BureauDeVote bureau){

    this.bureau = bureau ;

  }

  public void run(){
    try{
      dso=new DatagramSocket(bureau.getUdpPort());
      data=new byte[1024];
      paquet=new DatagramPacket(data,data.length);
    }
    catch(Exception e )
    {
      e.printStackTrace();
    }
    while(true)
    {
      try
      {
        dso.receive(this.paquet);
        String st=new String(paquet.getData(),0,paquet.getLength());

        System.out.println("============================================================");
        System.out.println("                         PORT UDP                           ");
        System.out.println("============================================================");
        System.out.println("Message  reçu: "+st);
        System.out.println("De la machine:  "+paquet.getAddress().toString());
        Message message = new Message(st);
        Hashtable <String,String>result = message.messUDP();

        if(result.get("type").equals("ElectionChef"))
        {
          int numero = Integer.parseInt(result.get("numero"));
          ChangRoberts.receptionElection(bureau,numero);
        }

        if(result.get("type").equals("ChefElu"))
        {
          synchronized(bureau)
          {
            ChangRoberts.receptionElu(bureau,st,result.get("ip"),Integer.parseInt(result.get("port")));
          }
        }

      }
        catch(Exception e){
          e.printStackTrace();
        }
    }

  }


}
