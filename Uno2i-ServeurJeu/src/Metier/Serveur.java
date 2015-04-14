
package Metier;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author Martin
 */
public class Serveur extends Thread {
    
    ServerSocket srvsocket;
    private String etat;
    private String nom;
    
    public Serveur(String nom) {
        this.nom = nom;
        this.etat = "En attente de joueurs...";
    }
    
    @Override
    public void run() {
        
        // Configuration du serveur
        try {
            System.out.println("[Serveur] Serveur en cours de démarrage...");   
            srvsocket = new ServerSocket(0);
            
            System.out.println("[Serveur] Serveur démarré sur le port " + srvsocket.getLocalPort() + ".");
        }
        catch(IOException e) {
            System.err.println("[Serveur] Impossible de créer le socket serveur : " + e.getMessage());
        }
        
        // Notification de mise en ligne au serveur d'accueil
        Notification notif = new Notification(nom, etat);
        notif.start();
        
        System.out.println("[Serveur] Création des joueurs...");
        Joueur j1 = new Joueur("Joueur 1");
        Joueur j2 = new Joueur("Joueur 2");
        Joueur j3 = new Joueur("Joueur 3");
        Joueur j4 = new Joueur("Joueur 4");
        
        System.out.println("[Serveur] Création de la partie et ajout des joueurs...");
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
