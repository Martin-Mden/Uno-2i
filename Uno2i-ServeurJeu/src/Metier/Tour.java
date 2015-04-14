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
public class Tour {
    
    private boolean sensNormal;
    private ArrayList<Joueur> ordreJoueurs;
    private int indexJoueurActuel;
    
    public Tour(boolean sensNormal, ArrayList<Joueur> ordreJoueurs, int indexJoueurActuel) {
        this.sensNormal = sensNormal;
        this.ordreJoueurs = ordreJoueurs;
        this.indexJoueurActuel = indexJoueurActuel;
        
        System.out.print("[Tour] Nouveau tour, dans le sens ");
        if(this.sensNormal)
            System.out.print("normal. ");
        else
            System.out.print("inverse. ");
        
        System.out.print("C'est au tour de \"" + this.ordreJoueurs.get(indexJoueurActuel).getNom() + "\".");
        System.out.println("");
    }
    
    public Joueur getJoueurSuivant() {
        if((this.indexJoueurActuel+1) != (ordreJoueurs.size() - 1))
            return ordreJoueurs.get(this.indexJoueurActuel+1);
        else
            return this.tourSuivant().getJoueurSuivant();
    }  
    
    public Joueur getJoueurActuel() {
        return ordreJoueurs.get(this.indexJoueurActuel);
    }  
            
    public void inverserSens() {
        this.sensNormal = !this.sensNormal;
    }
    
    public Tour tourSuivant() {
        return new Tour(this.sensNormal, this.ordreJoueurs, this.indexJoueurActuel);
    }
    
}
