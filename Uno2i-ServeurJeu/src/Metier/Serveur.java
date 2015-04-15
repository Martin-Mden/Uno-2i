
package Metier;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin
 */
public class Serveur extends Thread {
    
    private ServerSocket srvsocket;
    private String etat;
    private String nom;
    private Regles regle;
    private Partie jeu;
    private Notification notif;
    
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
        
        ServeurJeu s = new ServeurJeu(this.srvsocket.getInetAddress().toString().split("/")[1], this.srvsocket.getLocalPort());
        s.setEtat("En attente de joueurs...");
        s.setNom(this.nom);
        
        // Notification de mise en ligne au serveur d'accueil
        notif = new Notification(s);
        notif.start();
        
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("[Serveur] Création des joueurs...");
        Joueur j1 = new Joueur("Joueur 1");
        Joueur j2 = new Joueur("Joueur 2");
        Joueur j3 = new Joueur("Joueur 3");
        Joueur j4 = new Joueur("Joueur 4");
        
        System.out.println("[Serveur] Création de la partie et ajout des joueurs...");
        jeu = new Partie();
        jeu.ajouterJoueur(j1);
        jeu.ajouterJoueur(j2);
        jeu.ajouterJoueur(j3);
        jeu.ajouterJoueur(j4);
        
        s.setEtat("Initialisation de la partie...");
        notif.notifier();
        
        jeu.initialiser();
        
        s.setEtat("Partie en cours.");
        notif.notifier();
        regle = new Regles(jeu);
    }
    
    public void stopper() {
        System.out.println("[Serveur] Serveur stoppé.");
    }
    
    public boolean test(String c){
        return regle.verifier(jeu.getJoueurs().get(0), new Carte(c));
    }
    
    public boolean poserCarte(String c){
        return jeu.jouer(new Carte(c), jeu.getTourActuel().getJoueurActuel());
    }
    
    public void deconnecter() {
        notif.deconnecter();
    }
    
     
}
