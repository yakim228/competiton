/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import competition.Joueur;
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
public class DBJoueur {
   private File fichier;
   public DBJoueur(String nom){
       fichier= new File(nom);
   }
   public void intDB(){
       ecrire(new ArrayList<Joueur>(), fichier);
   }
    public File ajouter(Joueur joueur){
        List<Joueur> liste = lire(fichier);
        liste.add(joueur);
        return ecrire(liste,fichier);
    }
    public File supprimer(int i){
        List<Joueur> liste = lire(fichier);
        liste.remove(i);
        return ecrire(liste,fichier);
    }
    public File update(Joueur joueur){
        List<Joueur> liste = lire(fichier);
        for(int i=0; i<all().size();i++){
            if(liste.get(i).equals(joueur)){
               liste.set(i,joueur);
            }
        }
        return ecrire(liste, fichier);
    }
    List<Joueur> lire(File file1){
        List<Joueur> liste = new ArrayList();
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
    File ecrire(List<Joueur> liste ,File file){
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
        for(Joueur p:lire(fichier)){
            System.out.println(p);
        }
    }
    public List<Joueur> all(){
        return lire(fichier);
    }
    public Joueur getJoueur(Joueur joueur,int o1){
        for(int i=0; i<all().size();i++){
            if(all().get(i).equals(joueur)){
                if(o1==-1){
                    supprimer(i);
                    return joueur;
                }
                return joueur;
                
            }
        }
        return null;
    }
}
