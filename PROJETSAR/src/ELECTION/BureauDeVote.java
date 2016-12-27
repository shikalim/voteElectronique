package ELECTION;

import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Random;

public class BureauDeVote {
    private int id;
    private int tcpPort;
    private int udpPort;
    private int nextUdpPort;
    private String myAdress;
    private String nextAdress;

    public BureauDeVote() {
        this.myAdress = this.myAdress();
        System.out.println(myAdress);
        this.tcpPort = this.tcpPort(this.myAdress);
        this.udpPort = this.udpPort();
        this.id = this.myId();
        this.nextUdpPort = this.udpPort;
        this.nextAdress = this.myAdress;
    }

    public BureauDeVote(int portUDP, String nextAdresse) {
    	
        this.myAdress = this.myAdress();
        this.tcpPort = this.tcpPort(this.myAdress);
        this.udpPort = this.udpPort();
        this.id = this.myId();
        this.nextUdpPort = portUDP;
        this.nextAdress = nextAdresse;
    }

    public int getId() {
        return this.id;
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

    public void setNextUdpPort(int n) {
        this.nextUdpPort = n;
    }

    public String getAdress() {
        return this.myAdress;
    }

    public String getNextAdress() {
        return this.nextAdress;
    }

    public void setNextAdress(String string) {
        this.nextAdress = string;
    }

    private int tcpPort(String adresse) {
    	Socket socket = null;
        for (int i = 1024; i < 10000; i++) {
            try {
            	socket = new Socket(adresse,i);
                socket.close();
                return i;
            }
            catch (Exception e) {
            	e.printStackTrace();
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

    public int myId() {
        Random random = new Random();
        return random.nextInt(10001);
    }

    public String toString() {
        return "Mon identifiant (\u00e9lection) : " + this.id + "\nMon port TCP : " + this.tcpPort + "\nMon port UDP : " + this.udpPort + "\nLe port du serveur avec quije communique :" + this.nextUdpPort + "\n Mon adresse :" + this.myAdress + "\nL'adresse du bureau de voteavec qui je communique :" + this.nextAdress;
    }
}