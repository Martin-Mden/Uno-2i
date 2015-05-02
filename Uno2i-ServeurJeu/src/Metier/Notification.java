
package Metier;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Notification extends Thread {
    
    private Socket socketServeurAccueil;
    private PrintWriter out;
    private String etat, nom;
    private ServeurJeu serveur;
    
    public Notification(ServeurJeu serveur) {
        this.serveur = serveur;
    }
    
    @Override
    public void run() {
        
        // Connexion au serveur d'accueil
        try {
            this.socketServeurAccueil = new Socket("127.0.0.1", 2000);
            this.out = new PrintWriter(this.socketServeurAccueil.getOutputStream(), true);
        }
        catch(UnknownHostException e) {
            System.err.println("[Notification] Impossible de se connecter au serveur d'accueil (Hôte inconnu) : " + e.getMessage());
        } 
        catch (IOException e) {
            System.err.println("[Notification] Impossible de se connecter au serveur d'accueil : " + e.getMessage());
        }
        
        while(true) {
            this.notifier();
            try {
                sleep(15000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Notification.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void deconnecter() {
        this.out.println("ISAI/");
    }
    
    public void notifier() {
        this.out.println("ISW/" + serveur.getNom() + ";" + serveur.getEtat() + ";" + String.valueOf(serveur.getPort()));            
    }
    
}
