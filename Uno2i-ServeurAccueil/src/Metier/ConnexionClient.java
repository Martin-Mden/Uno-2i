/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.DefaultListModel;

/**
 *
 * @author Martin
 */
public class ConnexionClient extends Thread {
    
    private Socket socketClient;
    private DefaultListModel listeServeurs, listeClients;
    private BufferedReader in;
    private PrintWriter out;
    private String trame;
    private Utilisateur utilisateur;
    private boolean connecte;
    
    public ConnexionClient(Socket socketClient, DefaultListModel listeServeurs, DefaultListModel listeClients, Utilisateur utilisateur) {
        this.socketClient = socketClient;
        this.listeServeurs = listeServeurs;
        this.listeClients = listeClients;
        this.utilisateur = utilisateur;
        this.connecte = true;
        
        System.out.println("[ConnexionClient] Un client s'est connecté.");
        this.listeClients.addElement(utilisateur.getPseudo());
    }
    
    @Override
    public void run() {
        
        try {
            this.in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            this.out = new PrintWriter(this.socketClient.getOutputStream(), true);
            String trameEnTete, trameContenu;
            
            while(connecte && (trame = this.in.readLine()) != null) {
                
                System.out.println("[ConnexionClient] Trame reçu : " + trame.split("/")[0]);
                
                // Analyse de la trame
                trameEnTete = trame.split("/")[0];
                
                trameContenu = "";
                if(trame.split("/").length != 1)
                    trameContenu = trame.split("/")[1];
                else
                    System.out.println("[ConnexionClient] Trame sans contenu.");
                
                if(trameEnTete.charAt(0) == 'C' && trameEnTete.charAt(1) == 'C') {
                    System.out.println("[ConnexionClient] Il s'agit d'une trame de connexion/inscription venant d'un client.");
                    if(trameEnTete.charAt(2) == 'C') {
                        System.out.println("[ConnexionClient] Il s'agit d'une connexion");
                        System.out.println("[ConnexionClient] Contenu/ Pseudo : " + trameContenu.split(";")[0]); 
                    }
                    else if(trameEnTete.charAt(2) == 'D' && trameEnTete.charAt(3) == 'I') {
                        // Déconnexion de l'utilisateur
                        connecte = false;
                    }
                    else if(trameEnTete.charAt(2) == 'A' && trameEnTete.charAt(3) == 'I') {
                        // Demande d'envoi des informations actualisées
                        System.out.println("[ConnexionClient] Demande d'actualisation reçu, on envoi les infos...");
                        String msg = "CLAR/";
                        for(int i = 0; i < this.listeServeurs.size(); i++) {
                            msg += ((ServeurJeu)this.listeServeurs.get(i)).getAdresseIp() + ":" + ((ServeurJeu)this.listeServeurs.get(i)).getPort() + ":" + ((ServeurJeu)this.listeServeurs.get(i)).getNom() + ":" + ((ServeurJeu)this.listeServeurs.get(i)).getEtat() + ";";
                        }
                        
                        msg = msg.substring(0, msg.length() - 1);
                        
                        this.out.println(msg);
                        System.out.println("[ConnexionClient] Envoyé.");
                    }
                }
            }
            
            listeClients.removeElement(utilisateur.getPseudo());
            System.out.println("[ConnexionClient] Un client s'est déconnecté.");
        }
        catch(IOException e) {
            System.err.println("[ConnexionClient] Erreur de communication avec le client : " + e);
            
            listeClients.removeElement(utilisateur.getPseudo());
            System.out.println("[ConnexionClient] Un client a été déconnecté prématurément.");
        }

    }

}
