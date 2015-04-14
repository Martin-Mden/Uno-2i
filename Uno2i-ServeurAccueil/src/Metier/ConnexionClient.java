/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    private String trame;
    private Utilisateur utilisateur;
    
    public ConnexionClient(Socket socketClient, DefaultListModel listeServeurs, DefaultListModel listeClients, Utilisateur utilisateur) {
        this.socketClient = socketClient;
        this.listeServeurs = listeServeurs;
        this.listeClients = listeClients;   
        this.utilisateur = utilisateur;
        
        System.out.println("[ConnexionClient] Un client s'est connecté.");
        this.listeClients.addElement(utilisateur.getPseudo());
    }
    
    @Override
    public void run() {
        
        try {
            this.in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            String trameEnTete, trameContenu;
            
            while((trame = this.in.readLine()) != null) {
                
                System.out.println("Trame reçu!");
                
                // Analyse de la trame
                trameEnTete = trame.split("/")[0];
                trameContenu = trame.split("/")[1];
                
                System.out.println("En-tête : " + trameEnTete);
                System.out.println("Contenu : " + trameContenu);
                if(trameEnTete.charAt(0) == 'C' && trameEnTete.charAt(1) == 'C') {
                    System.out.println("Il s'agit d'une trame de connexion/inscription venant d'un client.");
                    if(trameEnTete.charAt(2) == 'C') {
                        System.out.println("Ils'agit d'une connexion");
                        System.out.println("Contenu/ Pseudo : " + trameContenu.split(";")[0]); 
                    }
                }
            }
        }
        catch(IOException e) {
            System.err.println("[ConnexionClient] Erreur de communication avec le client : " + e);
        }
        
    }

}
