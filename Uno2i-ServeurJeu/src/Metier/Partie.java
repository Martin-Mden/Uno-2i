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
public class Partie {
    
    private ArrayList<Joueur> listeJoueurs;
    private Tour tourActuel;
    private Pioche pioche;
    
    public Partie() {
        this("Sans libellé");

    }

    public Tour getTourActuel() {
        return tourActuel;
    }
    
    public Partie(String libelle) { 
        this.listeJoueurs = new ArrayList<>();
        //System.out.println("[Partie] Création de la partie \"" + libelle + "\".");        
    }
    
    public ArrayList<Joueur> getJoueurs() {
        return this.listeJoueurs;
    }
    
    public void ajouterJoueur(Joueur j) {
        this.listeJoueurs.add(j);
        System.out.println("[Partie] Connexion de \"" + j.getNom() + "\" à la partie.");
    }
    
    public Pioche getPioche() {
        return this.pioche;
    }
    
    public void initialiser() {
        
        this.pioche = new Pioche();

        System.out.println("[Partie] Distribution des cartes...");

        for(int compteur = 0; compteur < 7; compteur++) {
            for(Joueur j : this.getJoueurs()) {
                j.getMain().ajouterCarte(this.getPioche().piocher());
            }
        }
        
        System.out.println("");
        for(Joueur j : this.getJoueurs()) {
            System.out.println("Contenu de la main du joueur \"" + j.getNom() + "\" :");
            for(Carte c : j.getMain().getCartes()) {
                System.out.print(c.getId() + ", ");
            }
            System.out.println("");
        }
        
        System.out.println("");
        
        this.getPioche().setDefausse(this.getPioche().piocher());
        System.out.println("Carte retournée en défausse : " + this.getPioche().getDefausse().getId());
   
        System.out.println("");
        this.tourActuel = new Tour(true, this.getJoueurs(), 0);
    }
    
}
