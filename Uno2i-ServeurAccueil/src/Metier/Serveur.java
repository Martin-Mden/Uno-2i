/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import GUI.ServeurAccueil;
import java.awt.FlowLayout;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Martin
 */
public class Serveur extends Thread {
    
    ServerSocket socketServeurAccueil;
    Socket socketServeurJeu;
    DefaultListModel listeServeurs;
    
    public Serveur(DefaultListModel listeServeurs) {
        this.listeServeurs = listeServeurs;
    }
    
    @Override
    public void run() {
        try {
            this.socketServeurAccueil = new ServerSocket(2000);
            System.out.println("[Serveur] Serveur disponible sur le port " + this.socketServeurAccueil.getLocalPort() + ".");
        }
        catch(IOException e) {
            System.err.println("[Serveur] La création du socket pour le serveur d'accueil a échoué.");
        }
        
        // En l'attente de connexion d'un serveur de jeu
        while(this.socketServeurAccueil != null && !Thread.currentThread().isInterrupted()) {
            try {
                this.socketServeurJeu = socketServeurAccueil.accept();
                ServeurJeu s = new ServeurJeu(this.socketServeurJeu.getInetAddress().toString(), this.socketServeurJeu.getPort());
                Thread t = new ConnexionServeur(socketServeurJeu, listeServeurs);
                t.start();
                
                //Icon serveurImage = new ImageIcon(ServeurAccueil.class.getResource("**Images/icon-serveur.png"));
                System.out.println(getClass().getClassLoader().getResource("Images/icon-serveur.png").getPath());
                Icon serveurImage = new ImageIcon(getClass().getClassLoader().getResource("Images/icon-serveur.png"));
                JLabel serveurLabel = new JLabel(s.toString(), serveurImage, JLabel.LEFT);
                JPanel serveurPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                serveurPanel.add(serveurLabel);

                this.listeServeurs.addElement(serveurPanel);
                
                System.out.println("Element ajouté!");
            }
            catch(IOException e) {
                System.err.println("[Serveur] Une tentative de connexion à échoué.");
            }
        }
    }
    
}
