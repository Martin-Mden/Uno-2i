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
    
    private String libelle;
    private ArrayList<Joueur> listeJoueurs;
    private Tour tourActuel;
    private Pioche pioche;
    
    public Partie() {
        this("Sans libellé");

    }
    
    public Partie(String libelle) { 
        this.libelle = libelle;
        this.listeJoueurs = new ArrayList<>();
        System.out.println("[Partie] Création de la partie \"" + libelle + "\".");        
    }
    
    public ArrayList<Joueur> getJoueurs() {
        return this.listeJoueurs;
    }
    
    public String getLibelle() {
        return this.libelle;
    }
    
    public void ajouterJoueur(Joueur j) {
        this.listeJoueurs.add(j);
        System.out.println("[Partie] Ajout du joueur \"" + j.getNom() + "\" à la partie \"" + this.getLibelle() + "\".");
    }
    
    public void initialiser() {
        
        this.pioche = new Pioche();
        this.tourActuel = new Tour(true, this.getJoueurs(), 0);
        
        System.out.println("[Partie] Distribution des cartes...");
        // Pioche de 6 cartes de test
        
        System.out.println("");
        for(Joueur j : this.getJoueurs()) {
            for(int compteur = 0; compteur < 7; compteur++) {
                j.getMain().getCartes().add(this.pioche.piocher());
            }
            
            System.out.println("Contenu de la main du joueur \"" + j.getNom() + "\" :");
            for(Carte c : j.getMain().getCartes()) {
                System.out.print(c.getId() + ", ");
            }
            System.out.println("");
        }
    }
    
}
