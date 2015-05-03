
package Outils;

import Metier.Connexion;
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
    
}
