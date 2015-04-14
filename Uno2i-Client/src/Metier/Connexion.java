/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author Guillaume
 */
public class Connexion extends Thread{
    ServerSocket socketClient;

    public Connexion(ServerSocket socketClient) {
        this.socketClient = socketClient;
    }
    
    @Override
    public void run(){
        try{
            System.out.println("[Serveur Client] Le client se connecte");
            socketClient = new ServerSocket();
            System.out.println("[Serveur Client] Serveur client démarré sur le port "+socketClient.getLocalPort()+".");
        }
        catch(IOException e){
            System.err.println("[Serveur] Impossible de créer le socket serveur : " + e.getMessage());
        }
        
        while(this.socketClient != null && !Thread.currentThread().isInterrupted()) {
            
        }
    }
}
