/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import java.util.ArrayList;

/**
 *
 * @author Guillaume
 */
public class Regles {
    private Partie partie;

    public Regles(Partie partie) {
        this.partie=partie;
    }

    public Partie getPartie() {
        return partie;
    }
    
    public boolean verifier(Joueur joueur, Carte carteJouee){
        //On commence par tester si c'est bien le tour de la bonne personne
        if(this.getPartie().getTourActuel().getJoueurActuel().getNom().equals(joueur.getNom())){
            System.out.println("[Regles] C'est le bon joueur.");
            
            //On regarde maintenant si la carte jouée par le joueur est bien dans sa main            
            if(!joueur.getMain().possedeCarte(carteJouee)) {
                System.out.println("[Regles] Le joueur ne possède pas la carte " + carteJouee.getId() + ".");
                return false;
            }          

            //Si c'est une carte chiffre, (passe ton tour, changement de sens ou +2)
            if(!carteJouee.getCouleur().equals(this.partie.getPioche().getDefausse().getCouleur())) {
                if(!carteJouee.getValeur().equals(this.partie.getPioche().getDefausse().getValeur())) {
                    return false;
                }
            }                                    
        }
        else {
            System.out.println("[Regles] Pas le bon joueur!");
            return false;
        }
        
        //Toutes les conditions sont OKAYYYY
        System.out.println("[Regles] Carte correcte, c'est OKAYYY!");
        return true;
    }
}
