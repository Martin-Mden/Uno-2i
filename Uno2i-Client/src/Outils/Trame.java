
package Outils;

import Metier.ConnexionJeu;

public class Trame {
    
    private static ConnexionJeu connexionServeurJeu;
    
    public static void initialiser(ConnexionJeu c) {
        Trame.connexionServeurJeu = c;
    }
    
    public static void envoyer(String trame) {
        connexionServeurJeu.envoyerTrame(trame);
    }
    
}
