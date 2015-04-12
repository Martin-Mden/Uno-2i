/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Martin
 */
public class Pioche {
    
    private ArrayList<Carte> listeCartesJeu;
    private Carte defausse;
    
    public Pioche() {
        
        System.out.println("[Pioche] Création de la pioche. Ajout des 108 cartes du jeu...");
        // Ajout des cartes au jeu
        this.listeCartesJeu = new ArrayList<>();
        
        // Bleu
        this.listeCartesJeu.add(new Carte("#B0"));
        this.listeCartesJeu.add(new Carte("#B1"));
        this.listeCartesJeu.add(new Carte("#B1"));
        this.listeCartesJeu.add(new Carte("#B2"));
        this.listeCartesJeu.add(new Carte("#B2"));
        this.listeCartesJeu.add(new Carte("#B3"));
        this.listeCartesJeu.add(new Carte("#B3"));
        this.listeCartesJeu.add(new Carte("#B4"));
        this.listeCartesJeu.add(new Carte("#B4"));
        this.listeCartesJeu.add(new Carte("#B5"));
        this.listeCartesJeu.add(new Carte("#B5"));
        this.listeCartesJeu.add(new Carte("#B6"));
        this.listeCartesJeu.add(new Carte("#B6"));
        this.listeCartesJeu.add(new Carte("#B7"));
        this.listeCartesJeu.add(new Carte("#B7"));
        this.listeCartesJeu.add(new Carte("#B8"));
        this.listeCartesJeu.add(new Carte("#B8"));
        this.listeCartesJeu.add(new Carte("#B9"));
        this.listeCartesJeu.add(new Carte("#B9"));
        
        // Vert
        this.listeCartesJeu.add(new Carte("#V0"));
        this.listeCartesJeu.add(new Carte("#V1"));
        this.listeCartesJeu.add(new Carte("#V1"));
        this.listeCartesJeu.add(new Carte("#V2"));
        this.listeCartesJeu.add(new Carte("#V2"));
        this.listeCartesJeu.add(new Carte("#V3"));
        this.listeCartesJeu.add(new Carte("#V3"));
        this.listeCartesJeu.add(new Carte("#V4"));
        this.listeCartesJeu.add(new Carte("#V4"));
        this.listeCartesJeu.add(new Carte("#V5"));
        this.listeCartesJeu.add(new Carte("#V5"));
        this.listeCartesJeu.add(new Carte("#V6"));
        this.listeCartesJeu.add(new Carte("#V6"));
        this.listeCartesJeu.add(new Carte("#V7"));
        this.listeCartesJeu.add(new Carte("#V7"));
        this.listeCartesJeu.add(new Carte("#V8"));
        this.listeCartesJeu.add(new Carte("#V8"));
        this.listeCartesJeu.add(new Carte("#V9"));
        this.listeCartesJeu.add(new Carte("#V9"));
        
        // Rouge
        this.listeCartesJeu.add(new Carte("#R0"));
        this.listeCartesJeu.add(new Carte("#R1"));
        this.listeCartesJeu.add(new Carte("#R1"));
        this.listeCartesJeu.add(new Carte("#R2"));
        this.listeCartesJeu.add(new Carte("#R2"));
        this.listeCartesJeu.add(new Carte("#R3"));
        this.listeCartesJeu.add(new Carte("#R3"));
        this.listeCartesJeu.add(new Carte("#R4"));
        this.listeCartesJeu.add(new Carte("#R4"));
        this.listeCartesJeu.add(new Carte("#R5"));
        this.listeCartesJeu.add(new Carte("#R5"));
        this.listeCartesJeu.add(new Carte("#R6"));
        this.listeCartesJeu.add(new Carte("#R6"));
        this.listeCartesJeu.add(new Carte("#R7"));
        this.listeCartesJeu.add(new Carte("#R7"));
        this.listeCartesJeu.add(new Carte("#R8"));
        this.listeCartesJeu.add(new Carte("#R8"));
        this.listeCartesJeu.add(new Carte("#R9"));
        this.listeCartesJeu.add(new Carte("#R9"));
        
        // Jaune
        this.listeCartesJeu.add(new Carte("#J0"));
        this.listeCartesJeu.add(new Carte("#J1"));
        this.listeCartesJeu.add(new Carte("#J1"));
        this.listeCartesJeu.add(new Carte("#J2"));
        this.listeCartesJeu.add(new Carte("#J2"));
        this.listeCartesJeu.add(new Carte("#J3"));
        this.listeCartesJeu.add(new Carte("#J3"));
        this.listeCartesJeu.add(new Carte("#J4"));
        this.listeCartesJeu.add(new Carte("#J4"));
        this.listeCartesJeu.add(new Carte("#J5"));
        this.listeCartesJeu.add(new Carte("#J5"));
        this.listeCartesJeu.add(new Carte("#J6"));
        this.listeCartesJeu.add(new Carte("#J6"));
        this.listeCartesJeu.add(new Carte("#J7"));
        this.listeCartesJeu.add(new Carte("#J7"));
        this.listeCartesJeu.add(new Carte("#J8"));
        this.listeCartesJeu.add(new Carte("#J8"));
        this.listeCartesJeu.add(new Carte("#J9"));
        this.listeCartesJeu.add(new Carte("#J9"));
        
        // Cartes +2
        this.listeCartesJeu.add(new Carte("+B2"));
        this.listeCartesJeu.add(new Carte("+B2"));
        this.listeCartesJeu.add(new Carte("+V2"));
        this.listeCartesJeu.add(new Carte("+V2"));
        this.listeCartesJeu.add(new Carte("+R2"));
        this.listeCartesJeu.add(new Carte("+R2"));
        this.listeCartesJeu.add(new Carte("+J2"));
        this.listeCartesJeu.add(new Carte("+J2"));
        
        // Cartes inversion
        this.listeCartesJeu.add(new Carte("~B~"));
        this.listeCartesJeu.add(new Carte("~B~"));
        this.listeCartesJeu.add(new Carte("~V~"));
        this.listeCartesJeu.add(new Carte("~V~"));
        this.listeCartesJeu.add(new Carte("~R~"));
        this.listeCartesJeu.add(new Carte("~R~"));
        this.listeCartesJeu.add(new Carte("~J~"));
        this.listeCartesJeu.add(new Carte("~J~"));
        
        // Cartes saut de tour
        this.listeCartesJeu.add(new Carte(">B>"));
        this.listeCartesJeu.add(new Carte(">B>"));
        this.listeCartesJeu.add(new Carte(">V>"));
        this.listeCartesJeu.add(new Carte(">V>"));
        this.listeCartesJeu.add(new Carte(">R>"));
        this.listeCartesJeu.add(new Carte(">R>"));
        this.listeCartesJeu.add(new Carte(">J>"));
        this.listeCartesJeu.add(new Carte(">J>"));
        
        // Jokers
        this.listeCartesJeu.add(new Carte(".J."));
        this.listeCartesJeu.add(new Carte(".J."));
        this.listeCartesJeu.add(new Carte(".J."));
        this.listeCartesJeu.add(new Carte(".J."));
        
        // SuperJokers
        this.listeCartesJeu.add(new Carte("JJJ"));
        this.listeCartesJeu.add(new Carte("JJJ"));
        this.listeCartesJeu.add(new Carte("JJJ"));
        this.listeCartesJeu.add(new Carte("JJJ"));
        
        // Mélange de la pioche
        System.out.println("[Pioche] Mélange...");
        Collections.shuffle(listeCartesJeu);
    }
            
    public void melanger() {
        
    }
    
    public ArrayList<Carte> getCartes() {
        return this.listeCartesJeu;
    }
    
    public Carte getDefausse() {
        return this.defausse;
    }
    
    public void setDefausse(Carte c) {
        this.defausse = c;
    }
    
    public Carte piocher() {
        Carte cartePiochee = this.getCartes().get(0);
        this.getCartes().remove(0);
        return cartePiochee;
    }
    
}
