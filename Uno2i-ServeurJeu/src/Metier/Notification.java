/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Martin
 */
public class Notification extends Thread {
    
    Socket socketServeurAccueil;
    PrintWriter out;
    private String etat, nom;
    
    public Notification(String nom, String etat) {
        this.nom = nom;
        this.etat = etat;
    }
    
    @Override
    public void run() {
        
        // Connexion au serveur d'accueil
        try {
            this.socketServeurAccueil = new Socket("127.0.0.1", 2000);
            this.out = new PrintWriter(this.socketServeurAccueil.getOutputStream(), true);
        }
        catch(UnknownHostException e) {
            System.err.println("[Notification] Impossible de se connecter au serveur d'accueil (Hôte inconnu) : " + e.getMessage());
        } 
        catch (IOException e) {
            System.err.println("[Notification] Impossible de se connecter au serveur d'accueil : " + e.getMessage());
        }
        
        //while(true) {
            this.out.println("ISW/" + this.nom + ";" + this.etat);
            
        //}
    }
    
}
