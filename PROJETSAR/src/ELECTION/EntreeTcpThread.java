package ELECTION;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class EntreeTcpThread extends Thread {
    ServerSocket serveur;
    BureauDeVote bureau;

    public EntreeTcpThread(BureauDeVote bureauDeVote) {
        try {
            this.serveur = new ServerSocket(bureauDeVote.getTcpPort());
            this.bureau = bureauDeVote;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true) {
            try {

                    Socket socket = this.serveur.accept();
                    System.out.println("============================================================");
                    System.out.println("                         PORT TCP                           ");
                    System.out.println("============================================================");
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                    String string = "WELC " + this.bureau.getNextAdress() + " " + this.bureau.getNextUdpPort() + "\n";
                    printWriter.print(string);
                    printWriter.flush();
                    string = bufferedReader.readLine();
                    Message message = new Message(string);
                    Hashtable <String,String>hashtable = message.newCMess();
                    System.out.println("Message reçu: "+string);
                    System.out.println("result  de hastable" + hashtable);
                    String string2 = new String((String)hashtable.get("port"));
                    synchronized (bureau)
                    {
                      this.bureau.setNextUdpPort(Integer.parseInt(string2));
                      this.bureau.setNextAdress((String)hashtable.get("ip"));
                    }
                    string = "ACKC\n";
                    printWriter.print(string);
                    printWriter.flush();
                    socket.close();

                    System.out.println("Serveur de join apr\u00e8s vote apr\u00e8s join");
                    System.out.println(bureau);

            }
            catch (Exception e) {
                e.printStackTrace();
                continue;
            }

        }
    }
}
