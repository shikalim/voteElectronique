package ELECTION;

import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Random;
import java.net.ServerSocket;

public class BureauDeVote {
    private String id;
    private int numero;/*Entier utilisé pour réaliser l'élection */
    private int tcpPort;
    private int udpPort;
    private int portEntree; //Port utilisé pour lire depuis l'entrée standard
    private int nextUdpPort;
    private String myAdress;
    private String nextAdress;
    private boolean vote ; /*Vaut true si le bureau déjà participer à l'election du chef */
    private boolean chef ; /*Vaut true s'il est élu comme chef */

    public BureauDeVote() {
        this.myAdress = this.myAdress();
        System.out.println(myAdress);
        this.tcpPort = this.tcpPort();
        this.udpPort = this.udpPort();
        this.id = generateId();
        this.numero = myNumero();
        this.nextUdpPort = this.udpPort;
        this.nextAdress = this.myAdress;
        vote = false ;
    }

    public BureauDeVote(int portUDP, String nextAdresse) {

        this.myAdress = this.myAdress();
        this.tcpPort = this.tcpPort();
        this.udpPort = this.udpPort();
        this.id = generateId();
        this.numero = myNumero();
        this.nextUdpPort = portUDP;
        this.nextAdress = nextAdresse;
        vote = false ;
    }

    public String getId() {
        return this.id;
    }

    public boolean getVote()
    {
      return  vote;
    }

    /*Cette méthode renvoie false si le bureau de vote à déjà voté */
    public boolean voter(){
      if (vote) return false;
      vote = true ;
      return vote ;
    }

    public int getTcpPort() {
        return this.tcpPort;
    }

    public int getUdpPort() {
        return this.udpPort;
    }

    public int getNextUdpPort() {
        return this.nextUdpPort;
    }

    public void setNextUdpPort(int port) {
        this.nextUdpPort = port;
    }

    public int getPortEntree(){
      return portEntree;
    }
    public void setPortEntree(){
      portEntree = udpPort();
    }

    public String getAdress() {
        return this.myAdress;
    }

    public String getNextAdress() {
        return this.nextAdress;
    }

    public boolean EstChef(){
      return chef ;
    }

    public void elirChef(){
      chef = true ;
    }

    public void setNextAdress(String adresse) {
        this.nextAdress = adresse;
    }

    private int tcpPort() {
        for (int i = 1024; i < 10000; i++) {
            try {
                ServerSocket socket = new ServerSocket(i);
                socket.close();
                return i;
            }
            catch (Exception e) {
                continue;
            }
        }
        return -1;
    }

    private int udpPort() {
        for (int i = 1024; i < 10000; ++i) {
            try {
                DatagramSocket datagramSocket = new DatagramSocket(i);
                datagramSocket.close();
                return i;
            }
            catch (Exception e) {
                continue;
            }
        }
        return -1;
    }


    private String myAdress() {
        String string = null;
        try {
            Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
            while (enumeration.hasMoreElements()) {
                NetworkInterface networkInterface = enumeration.nextElement();
                Enumeration<InetAddress> enumeration2 = networkInterface.getInetAddresses();
                while (enumeration2.hasMoreElements()) {
                    InetAddress inetAddress = enumeration2.nextElement();
                    if (!(inetAddress instanceof Inet4Address) || inetAddress.isLoopbackAddress()) continue;
                    string = inetAddress.toString();
                    String[] arrstring = string.split("\\/");
                    return arrstring[1];
                }
            }
        }
        catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int myNumero() {
        Random random = new Random();
        return random.nextInt(10001);
    }

    public int getNumero(){
      return numero ;
    }

    public String toString() {
        return "Mon identifiant: " + this.id + "\nMon port TCP : " + this.tcpPort + "\nMon port UDP : " + this.udpPort  + "\nMon adresse :"+myAdress+
        "\nPort UDP de l'entité suivante: "+ this.nextUdpPort+
        "\nL'adresse du bureau de vot eavec qui je communique :" + this.nextAdress;
    }

    /*Gérerer Id d'un entité */
  public static String generateId()
  {

    String hostname="";
    try
    {
       InetAddress addr=InetAddress.getLocalHost();
        hostname=addr.getHostName();
     }
     catch (Exception e ){
       System.out.println("Erreur Generation indentifiant \n Message de InetAddress:"+e.getMessage());
       return null ;
     }

     if (hostname.length()>5){
       hostname=hostname.substring(0,5);
     }

    if (hostname.length()<8){
       String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
       int restant=8-hostname.length();

       for(int r=1;r<=restant;r++){
               int e=(int)(Math.random()*chars.length());
               hostname+=""+chars.charAt(e);
        }

    }

     return  hostname;

   }
}
