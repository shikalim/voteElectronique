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
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                    String string = "WELC " + this.bureau.getUdpPort() + " " + this.bureau.getNextAdress() + "\n";
                    printWriter.print(string);
                    printWriter.flush();
                    string = bufferedReader.readLine();
                    Message message = new Message(string);
                    Hashtable <String,String>hashtable = message.newCMess();
                    System.out.println("result " + hashtable);
                    String string2 = new String((String)hashtable.get("port"));
                    this.bureau.setNextUdpPort(Integer.parseInt(string2));
                    this.bureau.setNextAdress((String)hashtable.get("ip"));
                    string = "ACKC\n";
                    printWriter.print(string);
                    printWriter.flush();
                    socket.close();
                
            }
            catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            
        }
    }
}