package competition;
import java.lang.IllegalStateException;
public class Match {
	private Joueur joueur1;
	private Joueur joueur2;
	private Joueur vainqueur;
	public Match(){
            
        }
	public Match(Joueur j1, Joueur j2) {
		this.joueur1=j1;
                this.joueur2=j2;
	}

	public Joueur getJoueur1() {
		return joueur1;
	}
	public Joueur getJoueur2() {
		return joueur2;
	}
	public Joueur getVainqueur() {
            if(vainqueur==null) throw new IllegalStateException("Le match "+this+" n'a pas ete joue");
		return vainqueur;
	}
	public Joueur getVaincu() {
            if(joueur1.equals(getVainqueur())) return joueur2;
            if(joueur2.equals(getVainqueur())) return joueur1;
            if(vainqueur==null) throw new IllegalStateException("Le match "+this+" n'a pas ete joue");
            return null;
	}

	public void setVainqueur(Joueur vainqueur) {
            
                if(joueur1.equals(vainqueur)){
                    this.vainqueur = vainqueur;
                }else if(joueur2.equals(vainqueur)){
                    this.vainqueur = vainqueur;
                }else
                    throw new IllegalArgumentException("Assurez vous que c'est l'un des deux joueurs");    
	}

    @Override
    public String toString() {
        return "Match{" + "joueur1=" + joueur1 + ", joueur2=" + joueur2 + ", vainqueur=" + vainqueur + '}';
    }
	
}
