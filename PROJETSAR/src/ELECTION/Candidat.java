package ELECTION;


public class Candidat extends Electeur {
	
    String numCandidat;

    public Candidat(String string, String string2, String string3, String string4) {
        super(string, string2, string3);
        this.numCandidat = string4;
    }

    public String getNumCandidat() {
        return this.numCandidat;
    }

    public void setNumeroCandidat(String string) {
        this.numCandidat = string;
    }

    public String toString() {
        return super.toString() + "Num\u00e9ro candidat: " + this.numCandidat + "\n";
    }
    
}