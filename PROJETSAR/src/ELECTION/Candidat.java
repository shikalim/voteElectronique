package ELECTION;

public class Candidat extends Electeur {

	String numCandidat;		
	
	public Candidat(String numElecteur, String nom, String prenom, String numCandidat) {
		super(numElecteur, nom, prenom);
		this.numCandidat =numCandidat;
	}

	public String getNumCandidat(){
		return numCandidat;
	}

	public String  toString(){
		return super.toString()+" "+numCandidat;
	}
	
	
	
	
}
