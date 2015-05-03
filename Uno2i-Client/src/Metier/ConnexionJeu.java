
package Metier;

import Outils.Outils;
import Outils.Trame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ConnexionJeu extends Thread {
    
    private Socket socketServeurJeu;
    private BufferedReader in;
    private PrintWriter out;
    private String pseudo;
    private JFrame fenetre;
    
    public ConnexionJeu(Socket socketServeurJeu, String pseudo, JFrame fenetre) {
        this.pseudo = pseudo;
        this.socketServeurJeu = socketServeurJeu;
        this.fenetre = fenetre;        
    }
    
    @Override
    public void run() {
        
        try {
            this.out = new PrintWriter(this.socketServeurJeu.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(this.socketServeurJeu.getInputStream()));
            Trame.initialiser(this);
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
        
        // Communication avec le jeu
        try {
            String trameEnTete, trameContenu;                
            while((trame = this.in.readLine()) != null) {
                
                trameEnTete = trame.split("/")[0];

                trameContenu = "";
                if(trame.split("/").length != 1)
                    trameContenu = trame.split("/")[1];
                else
                    System.out.println("[ActualisationServeurs] Trame sans contenu.");
                
                if(trameEnTete.charAt(0) == 'J' && trameEnTete.charAt(1) == 'S' && trameEnTete.charAt(2) == 'D' && trameEnTete.charAt(3) == 'I') {
                    JLabel decompteLabel = Outils.getComponentByName(fenetre, "infoLabel");
                    if(Integer.valueOf(trameContenu) == 1) {
                        decompteLabel.setText("1 seconde avant le début de la partie...");
                    }
                    else if(Integer.valueOf(trameContenu) == 0) {
                        decompteLabel.setText("La partie va commencer...");
                    }
                    else {
                        decompteLabel.setText(trameContenu + " secondes avant le début de la partie...");
                    }
                    
                }
                else if(trameEnTete.charAt(0) == 'J' && trameEnTete.charAt(1) == 'S' && trameEnTete.charAt(2) == 'T' && trameEnTete.charAt(3) == 'I') {
                    JLabel infoLabel = Outils.getComponentByName(fenetre, "infoLabel");
                    if(trameContenu.equals(this.pseudo)) {
                        infoLabel.setText("C'est à vous de jouer !");
                        JButton bouton = Outils.getComponentByName(fenetre, "pretBouton");
                        bouton.setText("FINIR TOUR");
                        bouton.setEnabled(true);
                    }
                    else {
                        infoLabel.setText("C'est au tour de " + trameContenu + ".");
                    }                                         
                }
                
            }
        }
        catch(IOException e) {
            System.err.println("[ConnexionJeu] Erreur de récupération des trames.");
        }
    }
    
    public void deconnecter() {
        // Envoi d'une trame de déconnexion
        this.out.println("CCDI/");
    }
    
    public void indiquerPret() {
        this.out.println("JCPI/");
    }
    
    public void envoyerMessageChat(String message) {
        //TODO
    }
    
    public void envoyerTrame(String trame) {
        this.out.println(trame);
    }
    
}
