package ELECTION;


public class Election {
    public static void main(String[] args) {
        if (args.length >= 2 && args[0].equals("-serveur")) {
            ServeurDeVote serveurDeVote;
            if (args[1].equals("-new")) {
                serveurDeVote = new ServeurDeVote();
                serveurDeVote.lancer();
            }
            if (args.length == 4 && args[1].equals("-join")) {
                serveurDeVote = new ServeurDeVote(Integer.parseInt(args[2]), args[3]);
                serveurDeVote.inserer();
            }
        }
    }
}