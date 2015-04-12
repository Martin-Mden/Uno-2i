
package Metier;

/**
 *
 * @author Martin
 */
public class Serveur extends Thread {
    
    public Serveur() {
        System.out.println("[Serveur] Serveur en cours de démarrage...");        
    }
    
    @Override
    public void run() {
        
        System.out.println("Création des joueurs...");
        Joueur j1 = new Joueur("Joueur 1");
        Joueur j2 = new Joueur("Joueur 2");
        Joueur j3 = new Joueur("Joueur 3");
        Joueur j4 = new Joueur("Joueur 4");
        
        System.out.println("Création de la partie et ajout des joueurs...");
        Partie jeu = new Partie();
        jeu.ajouterJoueur(j1);
        jeu.ajouterJoueur(j2);
        jeu.ajouterJoueur(j3);
        jeu.ajouterJoueur(j4);
        
        jeu.initialiser();
    }
    
    public void stopper() {
        System.out.println("[Serveur] Serveur stoppé.");
    }
    
}
