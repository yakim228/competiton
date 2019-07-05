/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import competition.Federation;
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
public class FederationDB {
   private File fichier;
   public FederationDB(String nom){
       fichier= new File(nom);
   }
    public File ajouter(Federation federation){
        List<Federation> liste = lire(fichier);
        liste.add(federation);
        return ecrire(liste,fichier);
    }
    public File supprimer(int i){
        List<Federation> liste = lire(fichier);
        liste.remove(i);
        return ecrire(liste,fichier);
    }
    public File update(int i,Federation federation){
        List<Federation> liste = lire(fichier);
        liste.remove(i);
        liste.add(federation);
        return ecrire(liste, fichier);
    }
    List<Federation> lire(File file1){
        List<Federation> liste = new ArrayList();
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
    File ecrire(List<Federation> liste ,File file){
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
        for(Federation p:lire(fichier)){
            System.out.println(p);
        }
    }
    public List<Federation> all(){
        return lire(fichier);
    }
    public Federation getFederation(Federation federation,int o1){
        for(int i=0; i<all().size();i++){
            if(all().get(i).equals(federation)){
                if(o1==-1){
                    supprimer(i);
                    return federation;
                }
                return federation;
                
            }
        }
        return null;
    }
}
