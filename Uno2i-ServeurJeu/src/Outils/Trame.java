
package Outils;

import Metier.Connexion;
import Metier.Jeu.Joueur;
import java.util.ArrayList;

public class Trame {
    
    private static ArrayList<Connexion> listeConnexions = new ArrayList<>();
    
    public static void addConnexion(Connexion c) {
        Trame.listeConnexions.add(c);
    }
    
    public static void removeConnexion(Connexion c) {
        Trame.listeConnexions.remove(c);
    }
    
    public static void envoyer(String trame) {
        for(Connexion c : Trame.listeConnexions) {
            c.envoyerTrame(trame);
        }
    }
    
    public static void envoyerPour(String trame, Joueur j) {
        for(Connexion c : Trame.listeConnexions) {
            if(c.getJoueur().equals(j)) {
                c.envoyerTrame(trame);
                break;
            }                
        }
    }
    
    public static void envoyerSauf(String trame, Joueur j) {
        for(Connexion c : Trame.listeConnexions) {
            if(!c.getJoueur().equals(j)) {
                c.envoyerTrame(trame);
            }                
        }
    }
    
}
