
package Metier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnexionJeu extends Thread {
    
    private Socket socketServeurJeu;
    private BufferedReader in;
    private PrintWriter out;
    private String pseudo;
    
    public ConnexionJeu(Socket socketServeurJeu, String pseudo) {
        this.pseudo = pseudo;
        this.socketServeurJeu = socketServeurJeu;
    }
    
    @Override
    public void run() {
        
        try {
            this.out = new PrintWriter(this.socketServeurJeu.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(this.socketServeurJeu.getInputStream()));
        }
        catch(UnknownHostException e) {
            System.err.println("[Connexion] Impossible de se connecter au serveur de jeu (Hôte inconnu) : " + e.getMessage());
        } 
        catch (IOException e) {
            System.err.println("[Connexion] Impossible de se connecter au serveur de jeu : " + e.getMessage());
        }

        // Envoi du pseudo
        String trame = "CCCI/" + this.pseudo;
        this.out.println(trame);
        System.out.println("[ConnexionJeu] Trame envoyée : " + trame);
        
        while(true) {
            
        }
        
        
    }
    
    public void deconnecter() {
        // Envoi d'une trame de déconnexion
        this.out.println("CCDI/");
    }
    
    public void indiquerPret() {
        this.out.println("JCPI/");
    }
    
}
