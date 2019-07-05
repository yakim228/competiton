/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package competition;

import data.DBJoueur;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Administrator
 */
public class Tournoi {
    public static final int NB_POINTS_VICTOIRE=20;
    private boolean fini;
    public static int t;
    private List<Match> lesMatchsJoues=new ArrayList<Match>();
    private Match[] tourEnCours;
    DBJoueur dbjoueur = new DBJoueur("joueurs.tmp");
    //Match[] tabDesMatchs;
    boolean state;
    public Tournoi(List<Joueur> joueurs){
        if(joueurs.isEmpty()){
            state=true;
        }
        t=0;
        t+=1;
        System.out.println("TOUR "+t);
        this.tourEnCours = new Match[joueurs.size()/2];
        matchAjouter(joueurs, this.tourEnCours);
    }
    public boolean getState(){
        return state;
    }
    public boolean tourExist(){
        return tourEnCours.length!=0;
    }
    public List<Match> getLesMatchsJoues() {
        return lesMatchsJoues;
    }
    public Match[] getTour(){
        return tourEnCours;
    }
    
    public boolean isFini() {
        return fini;
    }

    public void setFini(boolean fini) {
        this.fini = fini;
    }
    
    /**
     *
     * @param joueurs
     * @param tourEnCours
     */
    public void matchAjouter(List<Joueur> joueurs,Match[] tourEnCours){
        Random r=new Random();
        this.tourEnCours = new Match[joueurs.size()/2];
        for(int i=0; i<this.tourEnCours.length;i++){
            
            int j1Index = r.nextInt(joueurs.size());
            Joueur j1 = joueurs.get(j1Index);
            joueurs.remove(j1Index);
            
            int j2Index = r.nextInt(joueurs.size());
            Joueur j2 = joueurs.get(j2Index);
            joueurs.remove(j2Index);
            
            this.tourEnCours[i]=new Match(j1, j2);
        }
    }
    public void joueUnTour() throws TournoiNonFiniException{
        t+=1;
        
        Random r=new Random();
        List<Joueur> vainqueurs=new ArrayList<Joueur>();
        if(fini){
            System.out.println("fin [Vaincu "+getVaincu()+" le vaiqueur "+getVainqueur()+"]");
        } else {
            System.out.println("TOUR "+t);
            for(Match match:tourEnCours){
                boolean choix = r.nextBoolean();
                if(choix){
                    Joueur j = match.getJoueur1();
                    j.ajouterPoint(NB_POINTS_VICTOIRE);
                    match.setVainqueur(j);
                    System.out.println(match.getJoueur1());
                }else{
                    Joueur j = match.getJoueur2();
                    j.ajouterPoint(NB_POINTS_VICTOIRE);
                    match.setVainqueur(j);
                    System.out.println(match.getJoueur2());
                }
                vainqueurs.add(match.getVainqueur());
                dbjoueur.update(match.getVainqueur());
                lesMatchsJoues.add(match);
            }
            if(tourEnCours.length==1){
                fini=true;
            }else{
                tourEnCours = new Match[tourEnCours.length/2];
                matchAjouter(vainqueurs,this.tourEnCours);
            }
                
        }
    }
    public Joueur getVainqueur() throws TournoiNonFiniException{
        if(fini) return tourEnCours[0].getVainqueur();
        else{
            throw new TournoiNonFiniException();
        }
    }
    public Joueur getVaincu() throws TournoiNonFiniException{
        if(fini) return tourEnCours[0].getVaincu();
        else{
            throw new TournoiNonFiniException();
        }
    }
    public List<Match> matchsJoues()throws TournoiNonFiniException{
        if(fini) return lesMatchsJoues;
        else{
            throw new TournoiNonFiniException();
        }
    }
    
}
