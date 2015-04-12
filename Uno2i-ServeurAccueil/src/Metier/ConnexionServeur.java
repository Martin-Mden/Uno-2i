/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import java.net.Socket;
import javax.swing.DefaultListModel;

/**
 *
 * @author Martin
 */
public class ConnexionServeur extends Thread {
    
    Socket socketServeurJeu;
    DefaultListModel listeServeurs;
    
    public ConnexionServeur(Socket socketServeurJeu, DefaultListModel listeServeurs) {
        this.socketServeurJeu = socketServeurJeu;
        this.listeServeurs = listeServeurs;
        
        System.out.println("[ConnexionServeur] Un serveur de jeu s'est connecté : " + this.socketServeurJeu.getInetAddress().toString() + ":" + this.socketServeurJeu.getPort());
    }
    
    @Override
    public void run() {
        
        
        
    }
    
}
