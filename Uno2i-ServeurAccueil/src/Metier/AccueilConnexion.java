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
public class AccueilConnexion extends Thread {
    
    private Socket socket;
    private DefaultListModel listeServeurs;
    private BufferedReader in;
    private String trame;
    
    public AccueilConnexion(Socket socket, DefaultListModel listeServeurs) {
        this.socket = socket;
        this.listeServeurs = listeServeurs;
    }
    
    @Override
    public void run() {
        
        try {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String trameEnTete, trameContenu;
            boolean reparti = false;
            
            while(!reparti && (trame = this.in.readLine()) != null) {
                
                System.out.println("Trame reçu!");
                // Analyse de la trame
                trameEnTete = trame.split("/")[0];
                trameContenu = trame.split("/")[1];
                
                if(trameEnTete.charAt(0) == 'I' && trameEnTete.charAt(1) == 'S') { // Trame d'info en provenance du serveur de jeu
                    
                    if(trameEnTete.charAt(2) == 'W') { // Renseignement IP, nom, état
                        System.out.println("Le serveur de jeu renseigne sur son état actuel.");
                        System.out.println("Contenu/ Nom du serveur : " + trameContenu.split(";")[0] + " // État : " + trameContenu.split(";")[1] + " // Port : " + trameContenu.split(";")[2]);   
                        
                        // Mise à jour 
                        ServeurJeu s = new ServeurJeu(socket.getInetAddress().toString().split("/")[1], Integer.parseInt(trameContenu.split(";")[2]));
                        s.setNom(trameContenu.split(";")[0]);
                        s.setEtat(trameContenu.split(";")[1]);
                        
                        Thread t = new ConnexionServeur(socket, listeServeurs, s);
                        t.start();
                        reparti = true;
                    }
                }
                else if(trameEnTete.charAt(0) == 'C' && trameEnTete.charAt(1) == 'C') {
                    System.out.println("Il s'agit d'une trame de connexion/inscription venant d'un client.");
                    if(trameEnTete.charAt(2) == 'C') {
                        System.out.println("Il s'agit d'une connexion");
                        System.out.println("Contenu/ Pseudo : " + trameContenu.split(";")[0]); 
                        
                        Thread t = new ConnexionClient(socket, listeServeurs);
                        t.start();
                        reparti = true;
                    }
                }
            }
        }
        catch(IOException e) {
            System.err.println("[ConnexionServeur] Erreur de communication avec le serveur de jeu " + e);
        }
        
    }
    
}
