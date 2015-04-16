/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

/**
 *
 * @author Martin
 */
public class Joueur {
    
    private String nom;
    
    private Main mainJoueur;
    private boolean aPioche;
    
    public Joueur(String nom) {
        //System.out.println("[Joueur] Création du joueur \"" + nom + "\".");
        this.nom = nom;
        this.mainJoueur = new Main();
        this.aPioche = false;
    }
    
    public Main getMain() {
        return this.mainJoueur;
    }
    
    public String getNom() {
        return this.nom;
    }

    public boolean isaPioche() {
        return aPioche;
    }

    public void setaPioche(boolean aPioche) {
        this.aPioche = aPioche;
    }
    
}
