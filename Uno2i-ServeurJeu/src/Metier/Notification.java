/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Martin
 */
public class Notification extends Thread {
    
    Socket socketServeurAccueil;
    
    @Override
    public void run() {
        
        // Connexion au serveur d'accueil
        try {
            socketServeurAccueil = new Socket("127.0.0.1", 2000);
        }
        catch(UnknownHostException e) {
            System.err.println("[Notification] Impossible de se connecter au serveur d'accueil (Hôte inconnu) : " + e.getMessage());
        } 
        catch (IOException e) {
            System.err.println("[Notification] Impossible de se connecter au serveur d'accueil : " + e.getMessage());
        }
    }
    
}
