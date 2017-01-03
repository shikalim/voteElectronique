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

    public ServeurDeVote(int port, String adresse) {
        this.port = port;
        this.adresse = adresse;
        this.bureau = new BureauDeVote();
    }

    public void lancer() {
        System.out.println(bureau);
        EntreeTcpThread entreeTcpThread = new EntreeTcpThread(bureau);
        entreeTcpThread.start();
        EntreeUdpThread entreeUdpThread = new EntreeUdpThread(bureau);
        entreeUdpThread.start();
        EntreeStandardThread entreeStandardThread = new EntreeStandardThread(bureau);
        entreeStandardThread.start();

    }

    public void inserer() {
        try {
            System.out.println("=======================Debut insertion==========================");
            Socket socket = new Socket(this.adresse, this.port);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            String mess = bufferedReader.readLine();
            System.out.println("Message recu de l'entit\u00e9 :" + mess);
            Message message = new Message(mess);
            Hashtable<String, String> hashtable = message.insertMess();
            String reponse= "NEWC " + this.bureau.getAdress() + " " + this.bureau.getUdpPort() + "\n";
            System.out.println("Resultat de Hashtable " + hashtable);
            System.out.print(reponse);
            printWriter.print(reponse);
            printWriter.flush();
            mess = bufferedReader.readLine();
            printWriter.close();
            bufferedReader.close();
            socket.close();
            this.bureau.setNextAdress(hashtable.get("ip"));
            this.bureau.setNextUdpPort(Integer.parseInt(hashtable.get("port")));
            System.out.println("Serveur de join apr\u00e8s vote apr\u00e8s join");
            lancer();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
