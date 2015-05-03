
package Metier;

import Outils.Trame;
import Metier.Jeu.Partie;
import Metier.Jeu.Regles;
import Metier.Jeu.Carte;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Serveur extends Thread {
    
    private ServerSocket srvsocket;
    private String etat;
    private String nom;
    private Regles regle;
    private Partie jeu;
    private Notification notif;
    
    private PrintWriter out;
    
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
        
        notif = new Notification(s);
        notif.start(); 
        
        System.out.println("[Serveur] Création de la partie et attente de joueurs...");
        jeu = new Partie();
        
        AccueilConnexion connexion = new AccueilConnexion(srvsocket, this);
        connexion.start();
        
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        
        // Attente de connexion d'au moins 2 joueurs        
        while(this.jeu.getJoueurs().size() + this.jeu.getListeConnectesNonPrets().size() < 2) {                         
            
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
            }             
        }    
        
        // Attente de connexion de 4 joueurs pendant 5 minutes
        int decompte = 300;
        while(!this.jeu.getListeConnectesNonPrets().isEmpty() && this.jeu.getListeConnectesNonPrets().size() < 4 && decompte >= 0) {
            
            Trame.envoyer("JSDI/" + decompte);
            decompte--;
                
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        
        // Attente de 6 joueurs pendant 1 minutes
        if(decompte > 100) decompte = 100;
        while(!this.jeu.getListeConnectesNonPrets().isEmpty() && this.jeu.getListeConnectesNonPrets().size() < 6 && decompte >= 0) {

            Trame.envoyer("JSDI/" + decompte);
            decompte--;
            
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        
        Trame.envoyer("JSDI/0");
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
    
    public void piocher(){
        jeu.piocher(jeu.getTourActuel().getJoueurActuel());
    }
    
    public void passerSonTour(){
        jeu.passerTour();
    }
    
    public Partie getPartie() {
        return this.jeu;
    }
     
}
