/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import java.util.ArrayList;

/**
 *
 * @author Martin
 */
public class Main {
    
    private ArrayList<Carte> listeCartesEnMain;
    
    public Main() {
        this.listeCartesEnMain = new ArrayList<>();
    }
    
    public ArrayList<Carte> getCartes() {
        return this.listeCartesEnMain;
    }
    
    public void ajouterCarte(Carte c) {
        this.listeCartesEnMain.add(c);
    }
    
    public boolean possedeCarte(Carte c) {
        for(Carte carteMain: this.getCartes()) {
            if(c.getId().equals(carteMain.getId()))
                return true;
        }
        
        return false;
    }
    
}
