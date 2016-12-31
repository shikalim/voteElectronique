package ELECTION;

public class Electeur {
    private String numElecteur;
    private String nom;
    private String prenom;
    private boolean vote = false;

    public Electeur(String numElecteur, String nom, String prenom) {
        this.numElecteur = numElecteur;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNumElecteur() {
        return this.numElecteur;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public boolean getVote() {
        return this.vote;
    }

    public boolean voter() {
        if (this.vote) {
            return false;
        }
        this.vote = true;
        return true;
    }

    public String toString() {
        return "Num\u00e9ro \u00e9lecteur: " + this.numElecteur + "\nNom: " + this.nom + "\n Pr\u00e9nom: " + this.prenom + "\n";
    }
}