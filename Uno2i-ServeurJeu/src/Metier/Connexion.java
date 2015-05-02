
package Metier;

import Metier.Jeu.Joueur;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.DefaultListModel;

public class Connexion extends Thread {
    
    private final Socket socketClient;
    private DefaultListModel listeServeurs, listeClients;
    private BufferedReader in;
    private PrintWriter out;
    private Joueur joueur;
    private Serveur serveur;
    
    public Connexion(Socket socketClient, Joueur joueur, Serveur serveur) {
        this.socketClient = socketClient;
        this.joueur = joueur;
        this.serveur = serveur;
        System.out.println("[Connexion] Un client s'est connecté.");
    }
    
    @Override
    public void run() {
        
        try {
            this.in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            this.out = new PrintWriter(this.socketClient.getOutputStream(), true);
            String trameEnTete, trameContenu, trame;
            
            // Phase de connexion (récupération du nom d'utilisateur, ...)
            while((trame = this.in.readLine()) != null) {
                
                System.out.println("[Connexion] Trame reçu : " + trame.split("/")[0]);
                
                // Analyse de la trame
                trameEnTete = trame.split("/")[0];
                
                trameContenu = "";
                if(trame.split("/").length != 1)
                    trameContenu = trame.split("/")[1];
                else
                    System.out.println("[Connexion] Trame sans contenu.");
                
                if(trameEnTete.charAt(0) == 'C' && trameEnTete.charAt(1) == 'C' && trameEnTete.charAt(2) == 'C' && trameEnTete.charAt(3) == 'I') {
                    this.joueur.setNom(trameContenu);    
                    this.serveur.getPartie().ajouterJoueur(this.joueur);
                }
                else if(trameEnTete.charAt(0) == 'J' && trameEnTete.charAt(1) == 'C' && trameEnTete.charAt(2) == 'P' && trameEnTete.charAt(3) == 'I') {
                    this.serveur.getPartie().preparerJoueur(this.joueur);
                    
                    // Entre en phase de jeu
                    break;
                }
            }
            
            // Phase de jeu
            while((trame = this.in.readLine()) != null) {
                
                System.out.println("[Connexion] Trame reçu : " + trame.split("/")[0]);
                
                // Analyse de la trame
                trameEnTete = trame.split("/")[0];
                
                trameContenu = "";
                if(trame.split("/").length != 1)
                    trameContenu = trame.split("/")[1];
                else
                    System.out.println("[Connexion] Trame sans contenu.");
                
                if(trameEnTete.charAt(0) == 'C' && trameEnTete.charAt(1) == 'C') {
                    
                }
            }
           
        }
        catch(IOException e) {
            System.err.println("[Connexion] Erreur de communication avec le client : " + e);
            
            listeClients.removeElement(joueur.getNom());
            System.out.println("[Connexion] Un client a été déconnecté prématurément.");
        }

    }

    public void envoyerTrame(String trame) {
        this.out.println(trame);
        System.out.println("Trame envoyée : " + trame);
    }
}
