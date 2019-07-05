/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package competition;
import data.DBJoueur;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class Federation implements Serializable{
    private HashMap<String,Joueur> lesLicencies = new HashMap<>();
    public static int nl=1;
    private String nom;
    private Joueur recentj;
    DBJoueur dbjoueur = new DBJoueur("joueurs.tmp");
    static final int TAILLE =12;
    public Federation(){}
    public Federation(String nom){
        this.nom=nom;
    }
    public String getNom(){
        return nom;
    }
    public static String getNouveauNumeroLicence() throws NoSuchAlgorithmException, NoSuchProviderException{
        nl+=1;
        char[] ch = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    char[] c = new char[12];
    SecureRandom random = new SecureRandom();
    for (int i = 0; i < 12; i++) {
      c[i] = ch[random.nextInt(ch.length)];
    }

    return new String(c);
    }
    public HashMap<String,Joueur> getLicence(){
        return lesLicencies;
    }
    public void setLicence(List<Joueur> lesJoueurs){
        lesJoueurs.forEach((j) -> {
            lesLicencies.put(j.getNumLicence(), j);
        });
    }
    private int calculPointsGagnes(Joueur vainqueur, Joueur vaincu){
        if(vainqueur.getPoints()<vaincu.getPoints()){
            System.out.println(vainqueur.getPoints());
            int p = ((vainqueur.getPoints()-vaincu.getPoints())/20);
            vainqueur.ajouterPoint(p+5);
        }else{
            
            vainqueur.ajouterPoint(5);
        }
        return vainqueur.getPoints();
    }
    public void incrireNouveauLicencie(String nom, String prenom) throws NoSuchAlgorithmException, NoSuchProviderException{
        String nl= getNouveauNumeroLicence();
        lesLicencies.put(nl, new Joueur(nom, prenom, nl));
        recentj = getJoueur(nl);
    }
    public Joueur recentJoueur(){
        return recentj;
    }
    public void supprimer(String n){
        Joueur j = lesLicencies.remove(n);
        if(j==null){
            System.out.println("Ce Joueur n'existe pas");
            return;
        }
        System.out.println("Suppression effective du "+j);
    }
    public Joueur getJoueur(String n){
        Joueur j = lesLicencies.get(n);
        if(j==null){
            System.out.println("Ce Joueur n'existe pas");
            return j;
        }
        System.out.println("le joueur "+j.getNom()+" "+j.getPrenom());
        return j;
    }
    public int getJPoint(String n){
        Joueur j = lesLicencies.get(n);
        if(j==null){
            System.out.println("Ce Joueur n'existe pas");
            return 0;
        }
        System.out.println("le joueur "+j.getNom()+" a "+j.getPoints());
        return j.getPoints();
    }
    private Map<String,Integer> calculPointsAcquis(Tournoi tournoi){
        Map<String, Integer> pointAquis= new HashMap();
        for(int i=0;i<tournoi.getLesMatchsJoues().size();i++){
            Match match=tournoi.getLesMatchsJoues().get(i);
            pointAquis.put(match.getJoueur1().getNumLicence(), match.getJoueur1().getPoints());
            pointAquis.put(match.getJoueur2().getNumLicence(), match.getJoueur2().getPoints());
        }
        return pointAquis;
    }
    public void attribuePoints(Tournoi tournoi) throws TournoiNonFiniException{
        if(!tournoi.isFini()){
            throw new TournoiNonFiniException();
        }else{
            calculPointsGagnes(tournoi.getVainqueur(),tournoi.getVaincu());
            tournoi.getVainqueur().ajouterPoint(tournoi.NB_POINTS_VICTOIRE);
            calculPointsAcquis(tournoi);
            dbjoueur.update(tournoi.getVainqueur());
        }
        
//        for(Match m:tournoi.matchsJoues()){
//            
//            System.out.println(m.getVainqueur());
//        }
    }
}
