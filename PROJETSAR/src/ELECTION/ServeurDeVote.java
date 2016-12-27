package ELECTION;

import ELECTION.BureauDeVote;
import ELECTION.EntreeTcpThread;
import ELECTION.Message;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.util.Hashtable;

public class ServeurDeVote {
    private BureauDeVote bureau;
    int port;
    String adresse;

    public ServeurDeVote() {
        this.bureau = new BureauDeVote();
    }

    public ServeurDeVote(int n, String string) {
        this.port = n;
        this.adresse = string;
        this.bureau = new BureauDeVote();
    }

    public void lancer() {
        EntreeTcpThread entreeTcpThread = new EntreeTcpThread(this.bureau);
        entreeTcpThread.start();
    }

    public void inserer() {
        try {
            System.out.println("=======================Debut insertion==========================");
            Socket socket = new Socket(this.adresse, this.port);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            String string = bufferedReader.readLine();
            System.out.println("Message recu de l'entit\u00e9 :" + string);
            Message message = new Message(string);
            Hashtable<String, String> hashtable = message.insertMess();
            String string2 = "NEWC " + this.bureau.getAdress() + " " + this.bureau.getUdpPort() + "\n";
            System.out.println("Resultat de Hashtable " + hashtable);
            System.out.print(string2);
            printWriter.print(string2);
            printWriter.flush();
            string = bufferedReader.readLine();
            printWriter.close();
            bufferedReader.close();
            socket.close();
            this.bureau.setNextAdress(hashtable.get("ip"));
            this.bureau.setNextUdpPort(Integer.parseInt(hashtable.get("port")));
            System.out.println("Serveur de join apr\u00e8s vote apr\u00e8s join");
            System.out.println(this.bureau);
        }
        catch (Exception var1_2) {
            System.out.println(var1_2.getMessage());
            var1_2.printStackTrace();
        }
    }
}