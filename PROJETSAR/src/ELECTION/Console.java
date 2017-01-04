
import java.util.* ;
import java.io.*;
import java.net.*;

public class Console{
	public static void main(String args[]){
		int port = 4020;
        InetAddress hote = null;
        Socket sc = null;

        try {
            if (args.length >= 2) {
                hote = InetAddress.getByName(args[0]);
                port = Integer.parseInt(args[1]);
            } else {
                hote = InetAddress.getByName("localhost");
            }
        } catch (UnknownHostException e) {
            System.err.println("Machine inconnue :" + e);
        }
        try {

            sc = new Socket(hote,port);
            
            EcouteServeurThread est = new EcouteServeurThread(sc);

			        } catch (IOException e) {
            System.err.println("Impossible de creer la socket du client: " +e);
            }
            finally {
                try {
                    sc.close();
                } catch (IOException e) {}
            }
        }
}
