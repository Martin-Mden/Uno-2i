/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Guillaume
 */
public class Connexion extends Thread{
    ServerSocket socketClient;
    Socket socketServeurAccueil;
    PrintWriter out;
    String Pseudo;
    
    public Connexion(String Pseudo) {
        this.Pseudo = Pseudo;
    }
    
    @Override
    public void run(){
        
        try {
            // Connexion au serveur d'accueil
            this.socketServeurAccueil = new Socket("127.0.0.1", 2000);
            this.out = new PrintWriter(this.socketServeurAccueil.getOutputStream(), true);
        }
        catch(UnknownHostException e) {
            System.err.println("[Connexion] Impossible de se connecter au serveur d'accueil (Hôte inconnu) : " + e.getMessage());
        } 
        catch (IOException e) {
            System.err.println("[Connexion] Impossible de se connecter au serveur d'accueil : " + e.getMessage());
        }

        this.out.println("CCCI/" + this.Pseudo);
    }
    
    public void deconnecter() {
        // Envoi d'une trame de déconnexion
        this.out.println("CCDI/");
    }
}
