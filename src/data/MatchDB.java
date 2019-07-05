/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import competition.Match;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class MatchDB {
   private File fichier;
   public MatchDB(String nom){
       fichier= new File(nom);
   }
    public File ajouter(Match match){
        List<Match> liste = lire(fichier);
        liste.add(match);
        return ecrire(liste,fichier);
    }
    public File supprimer(int i){
        List<Match> liste = lire(fichier);
        liste.remove(i);
        return ecrire(liste,fichier);
    }
    public File update(Match match){
        List<Match> liste = lire(fichier);
        for(int i=0; i<all().size();i++){
            if(liste.get(i).equals(match)){
               liste.set(i,match);
            }
        }
        return ecrire(liste, fichier);
    }
    List<Match> lire(File file1){
        List<Match> liste = new ArrayList();
        try{
            FileInputStream fis = new FileInputStream(file1);
            ObjectInputStream oos = new ObjectInputStream(fis);
            do{
                Object o = (List) oos.readObject();
                if (o==null) break;
                liste =(List) o;
            }while(true);
            oos.close();
        }catch(Exception e){
            e.getMessage();
        }
        return liste;
    }
    File ecrire(List<Match> liste ,File file){
        try{
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(liste);
            oos.close();
        }catch(IOException e){
            e.getMessage();
        }
        
        return file;
    }
    public void select(){
        for(Match p:lire(fichier)){
            System.out.println(p);
        }
    }
    public List<Match> all(){
        return lire(fichier);
    }
    public Match getMatch(Match match,int o1){
        for(int i=0; i<all().size();i++){
            if(all().get(i).equals(match)){
                if(o1==-1){
                    supprimer(i);
                    return match;
                }
                return match;
                
            }
        }
        return null;
    }
}
