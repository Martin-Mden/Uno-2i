
package Metier.Jeu;

import Outils.Trame;

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
            
            //On regarde maintenant si la carte jouée par le joueur est bien dans sa main            
            if(!joueur.getMain().possedeCarte(carteJouee)) {
                System.out.println("[Regles] Le joueur ne possède pas la carte " + carteJouee.getId() + ".");
                Trame.envoyerPour("JSJE/Message;Vous tentez de jouer une carte que vous ne possédez pas ou plus.", joueur);
                return false;
            }          

            //Si c'est une carte chiffre, (passe ton tour, changement de sens ou +2)
            if(!carteJouee.getCouleur().equals(this.partie.getPioche().getDefausse().getCouleur())) {
                if(!carteJouee.getValeur().equals(this.partie.getPioche().getDefausse().getValeur())) {
                    Trame.envoyerPour("JSJE/Message;Vous ne pouvez pas jouer cette carte.", joueur);
                    return false;
                }
            }                                    
        }
        else {            
            System.out.println("[Regles] Pas le bon joueur!");
            Trame.envoyerPour("JSJE/Message;Ce n'est pas votre tour.", joueur);
            return false;
        }
        
        //Toutes les conditions sont OKAYYYY
        System.out.println("[Regles] Carte correcte, c'est OKAYYY!");
        return true;
    }
}
