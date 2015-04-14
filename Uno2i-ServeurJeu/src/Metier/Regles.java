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
        Joueur leJoueurDuTour = this.partie.getTourActuel().getJoueurActuel();
        //On commence par tester si c'est bien le tour de la bonne personne
        if(leJoueurDuTour.getNom().equals(joueur.getNom())){
            //On regarde maintenant si la carte jouée par le joueur est bien dans sa main
            ArrayList<Carte> listeCartes = leJoueurDuTour.getMain().getCartes();
            boolean flag = false;
            for(int i = 0; i < listeCartes.size(); i++){
                if(listeCartes.get(i).getId().equals(carteJouee.getId()))
                    flag = true;
            }
            //Si ce n'est pas le bon joueur, on refuse sa carte
            if(flag!=true)
                return false;
            
            
            //Si c'est une carte chiffre, (passe ton tour, changement de sens ou +2)
            if(carteJouee.getCouleur()!=this.partie.getPioche().getDefausse().getCouleur())
                if(carteJouee.getValeur()!=this.partie.getPioche().getDefausse().getValeur())
                    return false;
            
        }
        else{
            return false;
        }
        //Toutes les conditions sont OKAYYYY
        return true;
    }
}
