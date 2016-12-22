package ELECTION;

public class Electeur {

	private String numElecteur, nom, prenom;
	private boolean vote =false;

	
	public Electeur(String numElecteur, String nom, String prenom){
		this.numElecteur=numElecteur;
		this.nom=nom;
		this.prenom=prenom;
	}
	
	public String getNumElecteur(){
		return numElecteur;
	}
	
	public String getNom(){
		return nom;
	}
	
	public String getPrenom(){
		return prenom;
	}
	
	public boolean getVote(){
		return vote;
	}

	public boolean SetVote(){
		
		if (vote==true)
			return vote=false;
		else 
			return vote=true;
	
	}


	public String toString(){	
		return numElecteur+" "+nom+" "+prenom;		
	}
	
	
}
