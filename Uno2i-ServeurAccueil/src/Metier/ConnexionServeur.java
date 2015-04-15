/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import GUI.ServeurAccueil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.DefaultListModel;

/**
 *
 * @author Martin
 */
public class ConnexionServeur extends Thread {
    
    private Socket socketServeurJeu;
    private DefaultListModel listeServeurs;
    private ServeurJeu serveurJeu;
    private BufferedReader in;
    private String trame;
    
    public ConnexionServeur(Socket socketServeurJeu, DefaultListModel listeServeurs, ServeurJeu serveurJeu) {
        this.socketServeurJeu = socketServeurJeu;
        this.listeServeurs = listeServeurs;
        this.serveurJeu = serveurJeu;
        
        System.out.println("[ConnexionServeur] Un serveur de jeu s'est connecté : " + this.socketServeurJeu.getInetAddress().toString() + ":" + this.socketServeurJeu.getPort());   
        this.listeServeurs.addElement(serveurJeu); 
    }
    
    @Override
    public void run() {
        
        try {
            this.in = new BufferedReader(new InputStreamReader(socketServeurJeu.getInputStream()));
            String trameEnTete, trameContenu;
            boolean demarre = true;
            
            while(demarre && (trame = this.in.readLine()) != null) {
                
                System.out.println("Trame reçu!");
                // Analyse de la trame
                trameEnTete = trame.split("/")[0];
                
                trameContenu = "";
                if(trame.split("/").length != 1)
                    trameContenu = trame.split("/")[1];
                else
                    System.out.println("[ConnexionClient] Trame sans contenu.");
                
                System.out.println("En-tête : " + trameEnTete);
                System.out.println("Contenu : " + trameContenu);
                if(trameEnTete.charAt(0) == 'I' && trameEnTete.charAt(1) == 'S') { // Trame d'info en provenance du serveur de jeu
                    System.out.println("Il s'agit d'une trame d'information en provenance du serveur du jeu.");
                    if(trameEnTete.charAt(2) == 'W') { // Renseignement IP, nom, état
                        System.out.println("Le serveur de jeu renseigne sur son état actuel.");
                        System.out.println("Contenu/ Nom du serveur : " + trameContenu.split(";")[0] + " // État : " + trameContenu.split(";")[1]);   
                        
                        // Mise à jour 
                        ServeurJeu s = (ServeurJeu)this.listeServeurs.get(this.listeServeurs.indexOf(this.serveurJeu));
                        s.setNom(trameContenu.split(";")[0]);
                        s.setEtat(trameContenu.split(";")[1]);  
                        
                        ServeurAccueil.actualiser();
                    }
                    else if(trameEnTete.charAt(2) == 'A' && trameEnTete.charAt(3) == 'I') {
                        System.out.println("[ConnexionServeur] Le serveur de jeu informe de sa fermeture");
                        demarre = false;
                    }
                }
            }
            
            this.listeServeurs.removeElement(serveurJeu);
        }
        catch(IOException e) {
            System.err.println("[ConnexionServeur] Erreur de communication avec le serveur de jeu " + e);
            
            this.listeServeurs.removeElement(serveurJeu);
            System.out.println("[ConnexionServeur] Un serveur de jeu a été déconnecté prématurément.");
        }
        
    }
    
}
