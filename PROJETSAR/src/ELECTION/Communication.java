package ELECTION ;

import java.io.*;
import java.net.*;
import java.nio.*;
import java.util.*;

public class Communication{

  public static boolean envoyer(String message, String adresse, int port){
    try
    {
      byte[]data;
      data=message.getBytes();
      DatagramSocket dso=new DatagramSocket();
      DatagramPacket paquet=new DatagramPacket(data,data.length,InetAddress.getByName(adresse),port);
      dso.send(paquet);
      return true ;
    }

    catch(Exception e){
      e.printStackTrace();
      return false;
    }
  }


}
