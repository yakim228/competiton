package competition;

import java.io.Serializable;

/**
 *
 * 
 */
public class Joueur implements Comparable,Serializable {
	private String nom;
	private String prenom;
	private String numLicence;
	private int points;
	public Joueur() {}
	public Joueur(String nom, String prenom, String numLicence) {
		this.nom = nom;
		this.prenom = prenom;
		this.numLicence = numLicence;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNumLicence() {
		return numLicence;
	}
	public void setNumLicence(String numLicence) {
		this.numLicence = numLicence;
	}
        public void ajouterPoint(int Npoints){
            this.points+=Npoints;
        }

        public int getPoints() {
            return points;
        }
        
	@Override
	public String toString() {
		return "Joueur"+" [nom=" + nom + ", prenom=" + prenom + ", numLicence=" + numLicence + "] possede "+points+" point(s)";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((numLicence == null) ? 0 : numLicence.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		Joueur other = (Joueur) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (numLicence == null) {
			if (other.numLicence != null)
				return false;
		} else if (!numLicence.equals(other.numLicence))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		return true;
	}	
	@Override
	public int compareTo(Object autre) {
		// TODO Auto-generated method stub
		return points-(((Joueur) autre).points);
	}
        public Object[] toObject(){
            return new Object[] {numLicence,nom,prenom,points};
        }
        
}
